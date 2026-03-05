package com.videogenerator.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPaths @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val externalFilesDir: File
        get() = context.getExternalFilesDir(null) ?: context.filesDir

    val videoOutputDir: String
        get() = "${externalFilesDir.absolutePath}/generated/output/video".also {
            File(it).mkdirs()
        }

    val sliceTempDir: String
        get() = "${externalFilesDir.absolutePath}/generated/temp_slices".also {
            File(it).mkdirs()
        }

    val textTempDir: String
        get() = "${externalFilesDir.absolutePath}/generated/temp_text".also {
            File(it).mkdirs()
        }

    fun getFinalVideoOutputPath(): String =
        "$videoOutputDir/video_${System.currentTimeMillis()}.mp4"
}
