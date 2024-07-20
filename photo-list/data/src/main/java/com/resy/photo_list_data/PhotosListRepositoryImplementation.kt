package com.resy.photo_list_data

import com.resy.data.NetworkBoundResource
import com.resy.data.mapWith
import com.resy.domain.Results
import com.resy.models.PhotoItem
import com.resy.photo_list_data.mappers.PhotoItemMapper
import com.resy.photo_list_domain.IPhotosListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotosListRepositoryImplementation @Inject constructor(
    private val api: PhotoListApi,
    private val networkBoundResource: NetworkBoundResource,
    private val photoListMapper: PhotoItemMapper,
) : IPhotosListRepository {
    override suspend fun loadPhotos(): Flow<Results<List<PhotoItem>>> =
        networkBoundResource
            .fetch { api.loadPhotosList() }
            .mapWith(photoListMapper)
}
