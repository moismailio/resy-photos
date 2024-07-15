package com.resy.photos.photosList.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.resy.photos.R
import com.resy.photos.designsystem.ResyScaffold
import com.resy.photos.designsystem.ResyTopBar
import com.resy.photos.designsystem.colors
import com.resy.photos.designsystem.spacing
import com.resy.photos.designsystem.theme.Typography
import com.resy.photos.photosList.domain.models.PhotoItem

@Composable
fun PhotosListScreen(onItemClicked: (PhotoItem) -> Unit) {
    val viewModel: PhotosListViewModel = hiltViewModel()
    val photosUiState by viewModel.profileUiState.collectAsState()
    PhotosListContent(
        photosUiState = photosUiState,
        onItemClicked = onItemClicked,
        onReloadPhotos = viewModel::handleAction,
    )
}

@Composable
internal fun PhotosListContent(
    photosUiState: PhotosUiState,
    onItemClicked: (PhotoItem) -> Unit,
    onReloadPhotos: (PhotosListUiAction) -> Unit,
) {
    ResyScaffold(
        header = { ResyTopBar(title = stringResource(R.string.photos_list_screen_title)) },
    ) { paddingValues ->

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (photosUiState) {
                PhotosUiState.Loading -> CircularProgressIndicator()
                is PhotosUiState.Error ->
                    ErrorLoadingPhotos {
                        onReloadPhotos(PhotosListUiAction.ReloadList)
                    }
                is PhotosUiState.Success ->
                    photosList(photos = photosUiState.data) { item ->
                        onItemClicked(item)
                    }
            }
        }
    }
}

@Composable
private fun photosList(
    modifier: Modifier = Modifier,
    photos: List<PhotoItem>,
    onItemClicked: (PhotoItem) -> Unit,
) {
    val lazyColumnState = rememberLazyListState()

    // TODO : add composition local of
    LazyColumn(
        modifier = modifier,
        state = lazyColumnState,
        verticalArrangement =
            Arrangement.spacedBy(
                spacing.spaceXXS,
            ),
    ) {
        items(items = photos, key = {
            it.id
        }) { item ->
            // TODO : repalce spacings and color and typography
            Text(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClicked(item)
                        }.padding(spacing.spaceS),
                text = item.fileName,
                style = Typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = colors.primary,
            )
        }
    }
}

@Composable
private fun ErrorLoadingPhotos(onReloadClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = stringResource(R.string.error_loading_photos_title))
        Button(modifier = Modifier.padding(top = spacing.spaceS), onClick = onReloadClicked) {
            Text(text = stringResource(R.string.reload_title))
        }
    }
}
