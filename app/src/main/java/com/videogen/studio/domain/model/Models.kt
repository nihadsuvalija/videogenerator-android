package com.videogen.studio.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoBatchItem(
    val id: Long = 0,
    val batchName: String,       // e.g. "BATCH_001"
    val folderPath: String,      // absolute path on device
    val videoCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable

@Parcelize
data class ImageBatchItem(
    val id: Long = 0,
    val batchName: String,
    val folderPath: String,
    val imageCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable

enum class Resolution(val width: Int, val height: Int, val label: String) {
    HORIZONTAL_FULL_HD(1920, 1080, "Landscape 1080p"),
    VERTICAL_FULL_HD(1080, 1920, "Portrait 1080p"),
    SQUARE(1080, 1080, "Square 1080p"),
    HORIZONTAL_4K(3840, 2160, "Landscape 4K"),
    VERTICAL_4K(2160, 3840, "Portrait 4K")
}

enum class FontOption(val assetPath: String, val displayName: String) {
    HELVETICA("font/Helvetica.ttf", "Helvetica"),
    TIMES_NEW_ROMAN("font/times-new-roman.ttf", "Times New Roman")
}

enum class TextCategory(val assetPath: String, val displayName: String) {
    LOCK_IN("text/lock_in.json", "Lock In"),
    MOTIVATIONAL("text/motivational.json", "Motivational")
}

enum class GenerationType {
    VIDEO_CONCAT,
    IMAGE_LOOP
}

data class GenerationConfig(
    val type: GenerationType,
    val videoBatchId: Long? = null,
    val imageBatchId: Long? = null,
    val resolution: Resolution = Resolution.VERTICAL_FULL_HD,
    val textCategory: TextCategory? = null,
    val fontOption: FontOption = FontOption.TIMES_NEW_ROMAN,
    val fontSize: Int = 26,
    val includeLogo: Boolean = true,
    val logoScaleFactor: Double = 0.25,
    val imageDuration: Double = 0.2,
    val totalDuration: Double = 15.0,
    val repeatCount: Int = 1
)

data class GeneratedVideo(
    val id: Long = 0,
    val outputPath: String,
    val duration: Double,
    val width: Int,
    val height: Int,
    val type: GenerationType,
    val batchName: String,
    val createdAt: Long = System.currentTimeMillis()
)

data class TextEntry(
    val id: Int,
    val text: String
)

sealed class GenerationState {
    object Idle : GenerationState()
    data class Running(val progress: Int, val message: String) : GenerationState()
    data class Success(val video: GeneratedVideo) : GenerationState()
    data class Error(val message: String) : GenerationState()
}
