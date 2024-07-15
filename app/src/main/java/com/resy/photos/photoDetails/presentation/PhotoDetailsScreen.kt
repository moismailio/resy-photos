package com.resy.photos.photoDetails.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import com.resy.photos.R
import com.resy.photos.designsystem.ResyScaffold
import com.resy.photos.designsystem.ResyTopBar
import com.resy.photos.designsystem.TopBarBackIcon
import com.resy.photos.designsystem.sizing
import com.resy.photos.designsystem.spacing
import com.resy.photos.designsystem.theme.Typography
import com.resy.photos.photosList.domain.models.PhotoItem

@Composable
internal fun ProfileDetailsScreen(
    photoItem: PhotoItem,
    onBackClicked: () -> Unit,
) {
    ResyScaffold(
        header = {
            ResyTopBar(
                title = stringResource(R.string.photo_details_screen_title),
                navigationIcon = { TopBarBackIcon(onIconClicked = onBackClicked) },
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
            Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = if (photoItem.isPortraitImage) Arrangement.Top else Arrangement.Center,
        ) {
            UrlImage(photoItem = photoItem)
            Text(
                modifier = Modifier.testTag("author_name").padding(top = spacing.spaceXXS, start = spacing.spaceXXS),
                text = photoItem.author,
                style = Typography.bodyLarge,
            )
        }
    }
}

@Composable
private fun UrlImage(photoItem: PhotoItem) {
    var imageState by remember {
        mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Empty)
    }

    val calculatedHeight = calculateHeightFromAspectRatio(
        photoItem.width.toFloat(),
        photoItem.height.toFloat()
    )

    val painter =
        rememberAsyncImagePainter(
            model = photoItem.url,
            onState = { state ->
                imageState = state
            },
            imageLoader = LocalContext.current.imageLoader,
        )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(calculatedHeight.toDp()),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.testTag("photo").fillMaxSize(),
            painter = painter,
            contentDescription = photoItem.fileName,
        )
        when (imageState) {
            is AsyncImagePainter.State.Loading -> LoadingImage()
            is AsyncImagePainter.State.Error -> ErrorLoadingImage()
            else -> {}
        }
    }
}

@Composable
private fun calculateHeightFromAspectRatio(width: Float, height: Float): Float {
    val aspectRatio = width / height
    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels
    val calculatedHeight = screenWidth / aspectRatio
    return calculatedHeight
}

@Composable
fun Float.toDp() =
    with(LocalDensity.current) {
        this@toDp.toDp()
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
    Box(modifier = Modifier.testTag("error_loading").fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.size(sizing.scaleXXXL),
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = stringResource(
                R.string.error_loading_the_image_description
            )
        )
    }
}

internal val PhotoItem.isPortraitImage
    get() = height > width

