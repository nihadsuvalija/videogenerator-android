package com.videogenerator.data.repository

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.videogenerator.domain.model.*
import com.videogenerator.domain.repository.TextRepository
import com.videogenerator.domain.repository.VideoGeneratorRepository
import com.videogenerator.util.AppPaths
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import kotlin.math.ceil
import kotlin.random.Random

data class TextEntryJson(val id: Int, val text: String)

class VideoGeneratorRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val appPaths: AppPaths
) : VideoGeneratorRepository {

    private val gson = Gson()
    private val tag = "VideoGeneratorRepo"

    // ---------------------------------------------------------------------------
    // Public entry points
    // ---------------------------------------------------------------------------

    override suspend fun generateConcatenatedVideo(
        config: GenerationConfig,
        videoBatch: VideoBatchItem,
        onProgress: (Int, String) -> Unit
    ): GeneratedVideo = withContext(Dispatchers.IO) {

        onProgress(5, "Scanning video batch...")
        val videoFiles = scanMp4Files(videoBatch.folderPath)
        if (videoFiles.isEmpty()) error("No MP4 files found in ${videoBatch.folderPath}")

        onProgress(15, "Creating slices...")
        val slicePaths = createVideoSlices(videoFiles, config.resolution, onProgress)

        onProgress(60, "Building filter graph...")
        val inputs = slicePaths.toMutableList()

        val filterInputs = inputs.indices.joinToString("") { "[$it:v]" }
        var filterComplex = "${filterInputs}concat=n=${inputs.size}:v=1:a=0[concat_out]"
        var lastLabel = "[concat_out]"

        // Text overlay
        config.textCategory?.let { category ->
            val text = getRandomText(category)
            val fontPath = extractFontToCache(config.fontOption)
            val lines = text.text.split("\n")
            val lineSpacing = 6
            val totalHeight = lines.size * config.fontSize + (lines.size - 1) * lineSpacing

            lines.forEachIndexed { index, line ->
                val nextLabel = "[txt$index]"
                val safeLine = line.replace(":", "\\:").replace("'", "\\'")
                filterComplex += ";${lastLabel}drawtext=" +
                        "fontfile='${fontPath}':" +
                        "text='${safeLine}':" +
                        "fontsize=${config.fontSize}:" +
                        "fontcolor=white:" +
                        "x=(w-text_w)/2:" +
                        "y=(h-${totalHeight})/2+${index}*(${config.fontSize}+${lineSpacing}):" +
                        "borderw=1.0:" +
                        "bordercolor=black" +
                        nextLabel
                lastLabel = nextLabel
            }
        }

        // Logo overlay
        if (config.includeLogo) {
            val logoPath = extractLogoToCache()
            inputs.add(logoPath)
            val logoIndex = inputs.size - 1
            filterComplex += ";[${logoIndex}:v]format=rgba,scale=iw*${config.logoScaleFactor}:-1[logo_scaled]" +
                    ";${lastLabel}[logo_scaled]overlay=x=(main_w-overlay_w)/2:y=(main_h*0.85-overlay_h/2)[final_out]"
            lastLabel = "[final_out]"
        }

        // Final scale/pad
        filterComplex += ";${lastLabel}scale=${config.resolution.width}:${config.resolution.height}:" +
                "force_original_aspect_ratio=decrease," +
                "pad=${config.resolution.width}:${config.resolution.height}:(ow-iw)/2:(oh-ih)/2[output_res]"
        lastLabel = "[output_res]"

        val finalOutputPath = appPaths.getFinalVideoOutputPath()
        onProgress(75, "Encoding final video...")

        val cmd = buildList {
            add("-y")
            inputs.forEach { addAll(listOf("-i", it)) }
            addAll(listOf("-filter_complex", filterComplex))
            addAll(listOf("-map", lastLabel))
            addAll(listOf("-c:v", "libx264", "-crf", "18", "-preset", "ultrafast"))
            addAll(listOf("-profile:v", "high", "-level", "4.0", "-an"))
            add(finalOutputPath)
        }

        executeFFmpeg(cmd)

        onProgress(95, "Reading output details...")
        val info = probeVideo(File(finalOutputPath))
        onProgress(100, "Done!")

        GeneratedVideo(
            outputPath = finalOutputPath,
            duration = info.first,
            width = info.second,
            height = info.third,
            type = GenerationType.VIDEO_CONCAT,
            batchName = videoBatch.batchName
        )
    }

    override suspend fun generateImageLoopVideo(
        config: GenerationConfig,
        imageBatch: ImageBatchItem,
        onProgress: (Int, String) -> Unit
    ): GeneratedVideo = withContext(Dispatchers.IO) {

        onProgress(5, "Scanning image batch...")
        val imageFiles = scanJpgFiles(imageBatch.folderPath)
        if (imageFiles.isEmpty()) error("No JPG images found in ${imageBatch.folderPath}")

        cleanDir(appPaths.sliceTempDir)

        val fontPath = config.textCategory?.let { extractFontToCache(config.fontOption) }
        val logoPath = if (config.includeLogo) extractLogoToCache() else null
        val textLines: List<String>? = config.textCategory?.let {
            getRandomText(it).text.split("\n")
        }

        val slicesNeeded = ceil(config.totalDuration / config.imageDuration).toInt()
        val tempVideos = mutableListOf<String>()

        onProgress(10, "Generating $slicesNeeded image clips...")

        repeat(slicesNeeded) { index ->
            val imagePath = imageFiles.random()
            val tempVideoPath = "${appPaths.sliceTempDir}/slice_$index.mp4"
            val lineSpacing = 6

            val filterChain = StringBuilder()
            filterChain.append("scale=${config.resolution.width}:${config.resolution.height}:")
            filterChain.append("force_original_aspect_ratio=increase,")
            filterChain.append("crop=${config.resolution.width}:${config.resolution.height}")

            textLines?.let { lines ->
                val totalHeight = lines.size * config.fontSize + (lines.size - 1) * lineSpacing
                lines.forEachIndexed { lineIndex, line ->
                    val safeLine = line.replace(":", "\\:").replace("'", "\\'")
                    filterChain.append(",drawtext=fontfile='${fontPath}':")
                    filterChain.append("text='${safeLine}':")
                    filterChain.append("fontsize=${config.fontSize}:")
                    filterChain.append("fontcolor=white:")
                    filterChain.append("x=(w-text_w)/2:")
                    filterChain.append("y=(h-${totalHeight})/2+${lineIndex}*(${config.fontSize}+${lineSpacing}):")
                    filterChain.append("borderw=1.0:")
                    filterChain.append("bordercolor=black")
                }
            }

            val finalFilter = if (logoPath != null) {
                "[0:v]${filterChain},format=rgba[tmp];" +
                        "[1:v]scale=iw*${config.logoScaleFactor}:-1[logo_scaled];" +
                        "[tmp][logo_scaled]overlay=x=(main_w-overlay_w)/2:y=(main_h*0.85-overlay_h/2)[out]"
            } else {
                "[0:v]${filterChain}[out]"
            }

            val cmd = buildList {
                add("-y")
                addAll(listOf("-loop", "1", "-i", imagePath))
                if (logoPath != null) addAll(listOf("-i", logoPath))
                addAll(listOf("-t", config.imageDuration.toString(), "-r", "25"))
                addAll(listOf("-filter_complex", finalFilter))
                addAll(listOf("-map", "[out]", "-c:v", "libx264", "-pix_fmt", "yuv420p", tempVideoPath))
            }

            executeFFmpeg(cmd)
            tempVideos.add(tempVideoPath)

            val progress = 10 + (index.toFloat() / slicesNeeded * 70).toInt()
            onProgress(progress, "Generated clip ${index + 1}/$slicesNeeded")
        }

        onProgress(82, "Concatenating clips...")

        val concatFile = File("${appPaths.textTempDir}/concat_list.txt").apply {
            parentFile?.mkdirs()
            createNewFile()
            writeText(tempVideos.joinToString("\n") {
                "file '${File(it).absolutePath.replace("'", "'\\''")}'"
            })
        }

        val finalOutputPath = appPaths.getFinalVideoOutputPath()

        executeFFmpeg(listOf(
            "-y", "-f", "concat", "-safe", "0",
            "-i", concatFile.absolutePath,
            "-c:v", "libx264", "-pix_fmt", "yuv420p",
            finalOutputPath
        ))

        onProgress(95, "Reading output details...")
        val info = probeVideo(File(finalOutputPath))
        onProgress(100, "Done!")

        GeneratedVideo(
            outputPath = finalOutputPath,
            duration = info.first,
            width = info.second,
            height = info.third,
            type = GenerationType.IMAGE_LOOP,
            batchName = imageBatch.batchName
        )
    }

    // ---------------------------------------------------------------------------
    // Private helpers
    // ---------------------------------------------------------------------------

    private fun createVideoSlices(
        videoFiles: List<String>,
        resolution: Resolution,
        onProgress: (Int, String) -> Unit
    ): List<String> {
        cleanDir(appPaths.sliceTempDir)
        val slices = mutableListOf<String>()

        videoFiles.forEachIndexed { i, videoPath ->
            val duration = probeVideo(File(videoPath)).first
            val maxStart = (duration - 1.0).coerceAtLeast(0.0)
            val startTime = Random.nextDouble(0.0, maxStart)
            val sliceOutput = "${appPaths.sliceTempDir}/slice_${i}_${System.currentTimeMillis()}.mp4"

            executeFFmpeg(listOf(
                "-y", "-i", videoPath,
                "-ss", startTime.toString(),
                "-t", "1",
                "-c:v", "libx264", "-crf", "18", "-preset", "ultrafast",
                "-profile:v", "high", "-level", "4.0", "-an",
                "-vf", "format=yuv420p," +
                        "scale=${resolution.width}:${resolution.height}:force_original_aspect_ratio=increase," +
                        "crop=${resolution.width}:${resolution.height}:(iw-${resolution.width})/2:(ih-${resolution.height})/2," +
                        "setsar=1," +
                        "eq=contrast=1.0:saturation=0.25:brightness=0.05",
                sliceOutput
            ))

            slices.add(sliceOutput)
            onProgress(15 + (i.toFloat() / videoFiles.size * 40).toInt(),
                "Sliced video ${i + 1}/${videoFiles.size}")
        }

        return slices
    }

    private fun executeFFmpeg(args: List<String>) {
        val fullCmd = listOf("ffmpeg") + args
        Log.d(tag, "FFmpeg cmd: ${fullCmd.joinToString(" ")}")

        val process = ProcessBuilder(fullCmd)
            .redirectErrorStream(true)
            .start()

        val output = process.inputStream.bufferedReader().readText()
        val exitCode = process.waitFor()

        if (exitCode != 0) {
            Log.e(tag, "FFmpeg failed:\n$output")
            error("FFmpeg failed with exit code $exitCode")
        }
    }

    private fun probeVideo(file: File): Triple<Double, Int, Int> {
        val process = ProcessBuilder(
            "ffprobe", "-v", "error",
            "-select_streams", "v:0",
            "-show_entries", "stream=width,height,duration",
            "-of", "default=noprint_wrappers=1:nokey=0",
            file.absolutePath
        ).start()

        val output = process.inputStream.bufferedReader().readText()
        process.waitFor()

        val width = Regex("width=(\\d+)").find(output)?.groupValues?.get(1)?.toInt() ?: 0
        val height = Regex("height=(\\d+)").find(output)?.groupValues?.get(1)?.toInt() ?: 0
        val duration = Regex("duration=([\\d.]+)").find(output)?.groupValues?.get(1)?.toDouble() ?: 0.0

        return Triple(duration, width, height)
    }

    private fun scanMp4Files(folderPath: String): List<String> {
        val folder = File(folderPath)
        if (!folder.exists() || !folder.isDirectory) return emptyList()
        return folder.listFiles { f -> f.isFile && f.extension.lowercase() == "mp4" }
            ?.map { it.absolutePath } ?: emptyList()
    }

    private fun scanJpgFiles(folderPath: String): List<String> {
        val folder = File(folderPath)
        if (!folder.exists() || !folder.isDirectory) return emptyList()
        return folder.listFiles { f -> f.isFile && f.extension.lowercase() in listOf("jpg", "jpeg") }
            ?.map { it.absolutePath } ?: emptyList()
    }

    private fun getRandomText(category: TextCategory): TextEntryJson {
        val json = context.assets.open(category.assetPath).bufferedReader().readText()
        val type = object : TypeToken<List<TextEntryJson>>() {}.type
        val list: List<TextEntryJson> = gson.fromJson(json, type)
        return list.random()
    }

    private fun extractFontToCache(fontOption: FontOption): String {
        val cacheFile = File(context.cacheDir, fontOption.assetPath)
        cacheFile.parentFile?.mkdirs()
        if (!cacheFile.exists()) {
            context.assets.open(fontOption.assetPath).use { input ->
                FileOutputStream(cacheFile).use { output -> input.copyTo(output) }
            }
        }
        return cacheFile.absolutePath
    }

    private fun extractLogoToCache(): String {
        val cacheFile = File(context.cacheDir, "logo/waymark_noire_transparent.png")
        cacheFile.parentFile?.mkdirs()
        if (!cacheFile.exists()) {
            context.assets.open("logo/waymark_noire_transparent.png").use { input ->
                FileOutputStream(cacheFile).use { output -> input.copyTo(output) }
            }
        }
        return cacheFile.absolutePath
    }

    private fun cleanDir(path: String) {
        val dir = File(path).apply { if (!exists()) mkdirs() }
        dir.listFiles()?.forEach { it.delete() }
    }
}
