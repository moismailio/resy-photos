package com.resy.photos.photosList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.resy.photos.core.domain.Results
import com.resy.photos.photosList.domain.models.PhotoItem
import com.resy.photos.photosList.domain.usecases.LoadPhotosListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosListViewModel
@Inject
constructor(
    private val loadPhotosUseCase: LoadPhotosListUseCase,
) : ViewModel() {
    private val _profileUiState = MutableStateFlow<PhotosUiState>(PhotosUiState.Loading)
    val profileUiState = _profileUiState.asStateFlow()

    init {
        loadPhotos()
    }

    private fun loadPhotos() {
        viewModelScope.launch {
            loadPhotosUseCase().collect { response ->
                _profileUiState.value =
                    when (response) {
                        is Results.Success -> PhotosUiState.Success(response.data)
                        is Results.Error -> PhotosUiState.Error(response.throwable.message ?: "")
                        Results.Loading -> PhotosUiState.Loading
                    }
            }
        }
    }

    fun handleAction(action: PhotosListUiAction) {
        when (action) {
            PhotosListUiAction.ReloadList -> loadPhotos()
        }
    }
}

sealed interface PhotosUiState {
    data object Loading : PhotosUiState

    data class Success(
        val data: List<PhotoItem>,
    ) : PhotosUiState

    data class Error(
        val message: String,
    ) : PhotosUiState
}

sealed interface PhotosListUiAction {
    data object ReloadList : PhotosListUiAction
}
