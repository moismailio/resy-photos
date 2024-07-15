package com.resy.photos

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.resy.photos.navigation.LocalNavController
import com.resy.photos.photoDetails.presentation.navigateToPhotoDetailsScreen
import com.resy.photos.photoDetails.presentation.photoDetailsScreen
import com.resy.photos.photosList.presentation.PhotosListScreenRoute
import com.resy.photos.photosList.presentation.photosListScreen

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
        photoDetailsScreen(onBackClicked = navController::popBackStack)
    }
}
