package com.resy.photos.photoDetails.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.resy.photos.navigation.navController
import com.resy.photos.photosList.domain.models.PhotoItem

const val PhotoDetailsScreenRoute = "photoDetailsScreenRoute"

const val PhotoItemKey = "photoItemKey"

// TODO : implement a better navigator

fun NavController.navigateToPhotoDetailsScreen(photo: PhotoItem) {
    currentBackStackEntry?.savedStateHandle?.set(
        key = PhotoItemKey,
        value = photo,
    )
    navigate(PhotoDetailsScreenRoute)
}

fun NavGraphBuilder.photoDetailsScreen(onBackClicked: () -> Unit) {
    composable(
        route = PhotoDetailsScreenRoute,
    ) {
        val item =
            navController.previousBackStackEntry?.savedStateHandle?.get<PhotoItem>(
                PhotoItemKey,
            )

        item?.let { photo ->
            ProfileDetailsScreen(
                photoItem = photo,
                onBackClicked = onBackClicked,
            )
        }
    }
}
