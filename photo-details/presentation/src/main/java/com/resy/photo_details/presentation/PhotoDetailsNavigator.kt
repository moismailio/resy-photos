package com.resy.photo_details.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.resy.models.PhotoItem

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

fun NavGraphBuilder.photoDetailsScreen(
    navigationController: NavController,
    onBackClicked: () -> Unit
) {
    composable(
        route = PhotoDetailsScreenRoute,
    ) {
        val item =
            navigationController.previousBackStackEntry?.savedStateHandle?.get<PhotoItem>(
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
