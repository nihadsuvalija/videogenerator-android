package com.videogen.studio.domain.usecase

import com.videogen.studio.domain.model.GeneratedVideo
import com.videogen.studio.domain.model.GenerationConfig
import com.videogen.studio.domain.model.ImageBatchItem
import com.videogen.studio.domain.model.VideoBatchItem
import com.videogen.studio.domain.repository.GeneratedVideoRepository
import com.videogen.studio.domain.repository.ImageBatchRepository
import com.videogen.studio.domain.repository.VideoBatchRepository
import com.videogen.studio.domain.repository.VideoGeneratorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// --- Video Batch Use Cases ---

class GetAllVideoBatchesUseCase @Inject constructor(
    private val repository: VideoBatchRepository
) {
    operator fun invoke(): Flow<List<VideoBatchItem>> = repository.getAllBatches()
}

class AddVideoBatchUseCase @Inject constructor(
    private val repository: VideoBatchRepository
) {
    suspend operator fun invoke(batch: VideoBatchItem): Long = repository.insertBatch(batch)
}

class DeleteVideoBatchUseCase @Inject constructor(
    private val repository: VideoBatchRepository
) {
    suspend operator fun invoke(id: Long) = repository.deleteBatch(id)
}

// --- Image Batch Use Cases ---

class GetAllImageBatchesUseCase @Inject constructor(
    private val repository: ImageBatchRepository
) {
    operator fun invoke(): Flow<List<ImageBatchItem>> = repository.getAllBatches()
}

class AddImageBatchUseCase @Inject constructor(
    private val repository: ImageBatchRepository
) {
    suspend operator fun invoke(batch: ImageBatchItem): Long = repository.insertBatch(batch)
}

class DeleteImageBatchUseCase @Inject constructor(
    private val repository: ImageBatchRepository
) {
    suspend operator fun invoke(id: Long) = repository.deleteBatch(id)
}

// --- Generation Use Cases ---

class GenerateConcatenatedVideoUseCase @Inject constructor(
    private val generatorRepository: VideoGeneratorRepository,
    private val generatedVideoRepository: GeneratedVideoRepository
) {
    suspend operator fun invoke(
        config: GenerationConfig,
        videoBatch: VideoBatchItem,
        onProgress: (Int, String) -> Unit
    ): GeneratedVideo {
        val video = generatorRepository.generateConcatenatedVideo(config, videoBatch, onProgress)
        generatedVideoRepository.insertVideo(video)
        return video
    }
}

class GenerateImageLoopVideoUseCase @Inject constructor(
    private val generatorRepository: VideoGeneratorRepository,
    private val generatedVideoRepository: GeneratedVideoRepository
) {
    suspend operator fun invoke(
        config: GenerationConfig,
        imageBatch: ImageBatchItem,
        onProgress: (Int, String) -> Unit
    ): GeneratedVideo {
        val video = generatorRepository.generateImageLoopVideo(config, imageBatch, onProgress)
        generatedVideoRepository.insertVideo(video)
        return video
    }
}

// --- History Use Cases ---

class GetGenerationHistoryUseCase @Inject constructor(
    private val repository: GeneratedVideoRepository
) {
    operator fun invoke(): Flow<List<GeneratedVideo>> = repository.getAllVideos()
}

class DeleteGeneratedVideoUseCase @Inject constructor(
    private val repository: GeneratedVideoRepository
) {
    suspend operator fun invoke(id: Long) = repository.deleteVideo(id)
}
