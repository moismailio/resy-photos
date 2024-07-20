package com.resy.photo_list_presentation

sealed interface PhotosListUiAction {
    data object ReloadList : PhotosListUiAction
}