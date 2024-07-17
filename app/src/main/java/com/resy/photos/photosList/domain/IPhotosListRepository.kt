package com.resy.photos.photosList.domain

import com.resy.photos.core.domain.Results
import com.resy.models.PhotoItem
import kotlinx.coroutines.flow.Flow

interface IPhotosListRepository {
    suspend fun loadPhotos(): Flow<Results<List<com.resy.models.PhotoItem>>>
}
