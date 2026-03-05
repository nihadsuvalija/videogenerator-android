package com.videogen.studio.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.videogen.studio.domain.model.ImageBatchItem
import com.videogen.studio.domain.model.VideoBatchItem
import com.videogen.studio.domain.usecase.AddImageBatchUseCase
import com.videogen.studio.domain.usecase.AddVideoBatchUseCase
import com.videogen.studio.domain.usecase.DeleteImageBatchUseCase
import com.videogen.studio.domain.usecase.DeleteVideoBatchUseCase
import com.videogen.studio.domain.usecase.GetAllImageBatchesUseCase
import com.videogen.studio.domain.usecase.GetAllVideoBatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

sealed class BatchUiEvent {
    data class ShowError(val message: String) : BatchUiEvent()
    data class ShowSuccess(val message: String) : BatchUiEvent()
}

@HiltViewModel
class VideoBatchViewModel @Inject constructor(
    private val getAllVideoBatchesUseCase: GetAllVideoBatchesUseCase,
    private val addVideoBatchUseCase: AddVideoBatchUseCase,
    private val deleteVideoBatchUseCase: DeleteVideoBatchUseCase
) : ViewModel() {

    val batches: StateFlow<List<VideoBatchItem>> =
        getAllVideoBatchesUseCase()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _events = MutableSharedFlow<BatchUiEvent>()
    val events: SharedFlow<BatchUiEvent> = _events.asSharedFlow()

    fun addBatch(folderUri: Uri, folderPath: String) {
        viewModelScope.launch {
            try {
                val folder = File(folderPath)
                val videoCount = folder.listFiles { f ->
                    f.isFile && f.extension.lowercase() == "mp4"
                }?.size ?: 0

                if (videoCount == 0) {
                    _events.emit(BatchUiEvent.ShowError("No MP4 files found in selected folder"))
                    return@launch
                }

                val batchName = folder.name.let {
                    if (it.startsWith("BATCH_", ignoreCase = true)) it
                    else "BATCH_${it.uppercase().replace(" ", "_")}"
                }

                addVideoBatchUseCase(
                    VideoBatchItem(
                        batchName = batchName,
                        folderPath = folderPath,
                        videoCount = videoCount
                    )
                )
                _events.emit(BatchUiEvent.ShowSuccess("Added $batchName ($videoCount videos)"))
            } catch (e: Exception) {
                _events.emit(BatchUiEvent.ShowError(e.message ?: "Unknown error"))
            }
        }
    }

    fun deleteBatch(id: Long) {
        viewModelScope.launch {
            deleteVideoBatchUseCase(id)
            _events.emit(BatchUiEvent.ShowSuccess("Batch deleted"))
        }
    }
}

@HiltViewModel
class ImageBatchViewModel @Inject constructor(
    private val getAllImageBatchesUseCase: GetAllImageBatchesUseCase,
    private val addImageBatchUseCase: AddImageBatchUseCase,
    private val deleteImageBatchUseCase: DeleteImageBatchUseCase
) : ViewModel() {

    val batches: StateFlow<List<ImageBatchItem>> =
        getAllImageBatchesUseCase()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _events = MutableSharedFlow<BatchUiEvent>()
    val events: SharedFlow<BatchUiEvent> = _events.asSharedFlow()

    fun addBatch(folderPath: String) {
        viewModelScope.launch {
            try {
                val folder = File(folderPath)
                val imageCount = folder.listFiles { f ->
                    f.isFile && f.extension.lowercase() in listOf("jpg", "jpeg", "png")
                }?.size ?: 0

                if (imageCount == 0) {
                    _events.emit(BatchUiEvent.ShowError("No image files found in selected folder"))
                    return@launch
                }

                val batchName = folder.name.let {
                    if (it.startsWith("BATCH_", ignoreCase = true)) it
                    else "BATCH_${it.uppercase().replace(" ", "_")}"
                }

                addImageBatchUseCase(
                    ImageBatchItem(
                        batchName = batchName,
                        folderPath = folderPath,
                        imageCount = imageCount
                    )
                )
                _events.emit(BatchUiEvent.ShowSuccess("Added $batchName ($imageCount images)"))
            } catch (e: Exception) {
                _events.emit(BatchUiEvent.ShowError(e.message ?: "Unknown error"))
            }
        }
    }

    fun deleteBatch(id: Long) {
        viewModelScope.launch {
            deleteImageBatchUseCase(id)
            _events.emit(BatchUiEvent.ShowSuccess("Batch deleted"))
        }
    }
}
