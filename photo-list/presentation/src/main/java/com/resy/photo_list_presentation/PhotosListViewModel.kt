package com.resy.photo_list_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.resy.domain.Results
import com.resy.photo_list_domain.usecases.LoadPhotosListUseCase
import com.resy.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosListViewModel @Inject constructor(private val loadPhotosUseCase: LoadPhotosListUseCase) :
    BaseViewModel() {

    private val _profileUiState = MutableStateFlow<PhotosUiState>(PhotosUiState.Loading)
    val profileUiState = _profileUiState.asStateFlow()

    init {
        loadPhotos()
    }

    private fun loadPhotos() {
        viewModelScope.launch {
            loadPhotosUseCase().collect { response ->
                when (response) {
                    Results.Loading -> _profileUiState.value = PhotosUiState.Loading
                    is Results.Success -> _profileUiState.value =
                        PhotosUiState.Success(response.data)

                    is Results.Error -> {
                        processError(response.throwable)
                        _profileUiState.value = PhotosUiState.Error(response.throwable)
                    }
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