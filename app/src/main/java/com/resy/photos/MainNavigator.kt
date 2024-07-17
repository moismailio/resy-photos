package com.resy.photos

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.resy.photo_details.presentation.navigateToPhotoDetailsScreen
import com.resy.photo_details.presentation.photoDetailsScreen
import com.resy.photos.navigation.LocalNavController
import com.resy.photo_list_presentation.PhotosListScreenRoute
import com.resy.photo_list_presentation.photosListScreen

@Composable
fun MainNavigator(
    modifier: Modifier = Modifier,
    navController: NavHostController = LocalNavController.current,
) {
    NavHost(
        modifier = modifier,
        navController = LocalNavController.current,
        startDestination = PhotosListScreenRoute,
    ) {
        photosListScreen(onPhotoClicked = navController::navigateToPhotoDetailsScreen)
        photoDetailsScreen(
            navigationController = navController,
            onBackClicked = navController::popBackStack
        )
    }
}
