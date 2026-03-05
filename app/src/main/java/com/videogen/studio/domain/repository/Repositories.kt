package com.videogen.studio.domain.repository

import com.videogen.studio.domain.model.GeneratedVideo
import com.videogen.studio.domain.model.GenerationConfig
import com.videogen.studio.domain.model.ImageBatchItem
import com.videogen.studio.domain.model.TextEntry
import com.videogen.studio.domain.model.VideoBatchItem
import kotlinx.coroutines.flow.Flow

interface VideoBatchRepository {
    fun getAllBatches(): Flow<List<VideoBatchItem>>
    suspend fun getBatchById(id: Long): VideoBatchItem?
    suspend fun insertBatch(batch: VideoBatchItem): Long
    suspend fun deleteBatch(id: Long)
    suspend fun updateBatch(batch: VideoBatchItem)
}

interface ImageBatchRepository {
    fun getAllBatches(): Flow<List<ImageBatchItem>>
    suspend fun getBatchById(id: Long): ImageBatchItem?
    suspend fun insertBatch(batch: ImageBatchItem): Long
    suspend fun deleteBatch(id: Long)
    suspend fun updateBatch(batch: ImageBatchItem)
}

interface GeneratedVideoRepository {
    fun getAllVideos(): Flow<List<GeneratedVideo>>
    suspend fun getVideoById(id: Long): GeneratedVideo?
    suspend fun insertVideo(video: GeneratedVideo): Long
    suspend fun deleteVideo(id: Long)
}

interface VideoGeneratorRepository {
    suspend fun generateConcatenatedVideo(
        config: GenerationConfig,
        videoBatch: VideoBatchItem,
        onProgress: (Int, String) -> Unit
    ): GeneratedVideo

    suspend fun generateImageLoopVideo(
        config: GenerationConfig,
        imageBatch: ImageBatchItem,
        onProgress: (Int, String) -> Unit
    ): GeneratedVideo
}

interface TextRepository {
    suspend fun getRandomText(category: String): TextEntry
}
