package com.videogen.studio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.videogen.studio.domain.model.GeneratedVideo
import com.videogen.studio.domain.usecase.DeleteGeneratedVideoUseCase
import com.videogen.studio.domain.usecase.GetGenerationHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getGenerationHistoryUseCase: GetGenerationHistoryUseCase,
    private val deleteGeneratedVideoUseCase: DeleteGeneratedVideoUseCase
) : ViewModel() {

    val history: StateFlow<List<GeneratedVideo>> =
        getGenerationHistoryUseCase()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _events = MutableSharedFlow<String>()
    val events: SharedFlow<String> = _events.asSharedFlow()

    fun deleteVideo(id: Long) {
        viewModelScope.launch {
            deleteGeneratedVideoUseCase(id)
            _events.emit("Video removed from history")
        }
    }
}
