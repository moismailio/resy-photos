package com.resy.photos.photosList.data

import com.resy.photos.core.data.NetworkBoundResource
import com.resy.photos.core.domain.Results
import com.resy.photos.core.domain.mapWith
import com.resy.photos.photosList.domain.IPhotosListRepository
import com.resy.photos.photosList.domain.mappers.PhotoItemMapper
import com.resy.models.PhotoItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotosListRepositoryImplementation
    @Inject
    constructor(
        private val api: PhotoListApi,
        private val networkBoundResource: NetworkBoundResource,
        private val photoListMapper: PhotoItemMapper,
    ) : IPhotosListRepository {
        override suspend fun loadPhotos(): Flow<Results<List<com.resy.models.PhotoItem>>> =
            networkBoundResource
                .fetch { api.loadPhotosList() }
                .mapWith(photoListMapper)
    }
