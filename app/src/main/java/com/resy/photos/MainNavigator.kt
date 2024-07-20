package com.resy.photos

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.resy.photo_details.presentation.navigateToPhotoDetailsScreen
import com.resy.photo_details.presentation.photoDetailsScreen
import com.resy.photo_list_presentation.PhotosListScreenRoute
import com.resy.photo_list_presentation.photosListScreen

@Composable
fun MainNavigator(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = PhotosListScreenRoute,
    ) {
        photosListScreen(onPhotoClicked = navController::navigateToPhotoDetailsScreen)
        photoDetailsScreen(
            navController = navController,
            onBackClicked = navController::popBackStack
        )
    }
}
