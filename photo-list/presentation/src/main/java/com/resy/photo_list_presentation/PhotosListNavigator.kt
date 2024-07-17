package com.resy.photo_list_presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.resy.models.PhotoItem

const val PhotosListScreenRoute = "photosListRoute"

fun NavGraphBuilder.photosListScreen(onPhotoClicked: (PhotoItem) -> Unit) {
    composable(route = PhotosListScreenRoute) {
        PhotosListScreen(onItemClicked = onPhotoClicked)
    }
}
