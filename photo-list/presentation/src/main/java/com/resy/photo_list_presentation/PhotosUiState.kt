package com.resy.photo_list_presentation

import com.resy.models.PhotoItem

sealed interface PhotosUiState {
    data object Loading : PhotosUiState

    data class Success(val data: List<PhotoItem>) : PhotosUiState

    data class Error(val throwable: Throwable) : PhotosUiState
}
