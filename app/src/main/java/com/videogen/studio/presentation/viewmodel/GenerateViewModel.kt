package com.videogen.studio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.videogen.studio.domain.repository.ImageBatchRepository
import com.videogen.studio.domain.repository.VideoBatchRepository
import com.videogen.studio.domain.model.GenerationConfig
import com.videogen.studio.domain.model.GenerationState
import com.videogen.studio.domain.model.GenerationType
import com.videogen.studio.domain.model.ImageBatchItem
import com.videogen.studio.domain.model.VideoBatchItem
import com.videogen.studio.domain.usecase.GenerateConcatenatedVideoUseCase
import com.videogen.studio.domain.usecase.GenerateImageLoopVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateViewModel @Inject constructor(
    private val generateConcatenatedVideoUseCase: GenerateConcatenatedVideoUseCase,
    private val generateImageLoopVideoUseCase: GenerateImageLoopVideoUseCase,
    private val videoBatchRepository: VideoBatchRepository,
    private val imageBatchRepository: ImageBatchRepository
) : ViewModel() {

    private val _generationState = MutableStateFlow<GenerationState>(GenerationState.Idle)
    val generationState: StateFlow<GenerationState> = _generationState.asStateFlow()

    val videoBatches: StateFlow<List<VideoBatchItem>> =
        videoBatchRepository.getAllBatches()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val imageBatches: StateFlow<List<ImageBatchItem>> =
        imageBatchRepository.getAllBatches()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun generateVideo(config: GenerationConfig) {
        viewModelScope.launch {
            _generationState.value = GenerationState.Running(0, "Starting...")
            try {
                repeat(config.repeatCount) { iteration ->
                    val iterMsg = if (config.repeatCount > 1) " (${iteration + 1}/${config.repeatCount})" else ""

                    val video = when (config.type) {
                        GenerationType.VIDEO_CONCAT -> {
                            val batch = videoBatchRepository.getBatchById(config.videoBatchId!!)
                                ?: error("Video batch not found")
                            generateConcatenatedVideoUseCase(config, batch) { progress, message ->
                                _generationState.value = GenerationState.Running(progress, "$message$iterMsg")
                            }
                        }
                        GenerationType.IMAGE_LOOP -> {
                            val batch = imageBatchRepository.getBatchById(config.imageBatchId!!)
                                ?: error("Image batch not found")
                            generateImageLoopVideoUseCase(config, batch) { progress, message ->
                                _generationState.value = GenerationState.Running(progress, "$message$iterMsg")
                            }
                        }
                    }

                    if (iteration == config.repeatCount - 1) {
                        _generationState.value = GenerationState.Success(video)
                    }
                }
            } catch (e: Exception) {
                _generationState.value = GenerationState.Error(e.message ?: "Generation failed")
            }
        }
    }

    fun resetState() {
        _generationState.value = GenerationState.Idle
    }
}
