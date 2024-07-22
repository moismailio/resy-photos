package com.resy.design_system.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import com.resy.design_system.R
import com.resy.design_system.locals.sizing

@Composable
fun UrlImage(
    modifier: Modifier = Modifier,
    url: String,
    fileName: String,
    contentScale: ContentScale = ContentScale.Fit
) {

    var imageState by remember {
        mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Empty)
    }

    val painter =
        rememberAsyncImagePainter(
            model = url,
            onState = { state ->
                imageState = state
            },
            imageLoader = LocalContext.current.imageLoader,
        )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier
                .testTag(fileName)
                .fillMaxSize(),
            painter = painter,
            contentScale = contentScale,
            contentDescription = fileName,
        )
        when (imageState) {
            is AsyncImagePainter.State.Loading -> LoadingImage()
            is AsyncImagePainter.State.Error -> ErrorLoadingImage()
            else -> {}
        }
    }
}

@Composable
private fun LoadingImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorLoadingImage() {
    Box(
        modifier = Modifier
            .testTag("error_loading")
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(sizing.scaleXXXL),
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = stringResource(
                R.string.error_loading_the_image_description
            )
        )
    }
}
