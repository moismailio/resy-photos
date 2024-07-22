package com.resy.photo_list_presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.resy.design_system.components.ResyScaffold
import com.resy.design_system.components.ResyTopBar
import com.resy.design_system.components.UrlImage
import com.resy.design_system.locals.spacing
import com.resy.design_system.theme.Typography
import com.resy.design_system.utils.colors
import com.resy.models.PhotoItem

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
                .padding(paddingValues)
                .padding(16.dp),
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
                    PhotosList(photos = photosUiState.data) { item ->
                        onItemClicked(item)
                    }
            }
        }
    }
}

@Composable
private fun PhotosList(
    modifier: Modifier = Modifier,
    photos: List<PhotoItem>,
    onItemClicked: (PhotoItem) -> Unit,
) {
    val lazyColumnState = rememberLazyListState()
    LazyColumn(
        modifier = modifier,
        state = lazyColumnState,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(
            items = photos,
            key = { it.id }) { item ->
            PhotoRow(modifier = Modifier.fillMaxWidth(), item, onItemClicked)
//            PhotoRow(item = item,onItemClicked)
        }
    }
}

//@Composable
//private fun PhotoRow(item: PhotoItem, onItemClicked: (PhotoItem) -> Unit) {
//    Text(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onItemClicked(item) }
//            .padding(spacing.spaceS),
//        text = item.fileName,
//        style = Typography.bodyLarge,
//        textAlign = TextAlign.Center,
//        color = colors.primary,
//    )
//}

@Composable
private fun PhotoRow(modifier: Modifier, item: PhotoItem, onItemClicked: (PhotoItem) -> Unit) {
    Card(modifier.clickable {
        onItemClicked(item)
    }, elevation = CardDefaults.cardElevation(8.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp), contentAlignment = Alignment.TopStart
        ) {
            UrlImage(
                modifier = Modifier.fillMaxSize(),
                url = item.url,
                fileName = item.fileName,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .padding(spacing.spaceS)
                    .background(
                        color = colors.onSurface,
                        shape = RoundedCornerShape(spacing.spaceXXS)
                    )
                    .padding(spacing.spaceXXS),
                text = item.fileName,
                color = colors.surface,
                style = Typography.headlineSmall,
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
