package com.resy.photo_list_domain

import com.resy.models.PhotoItem
import kotlinx.coroutines.flow.Flow

interface IPhotosListRepository {
    suspend fun loadPhotos(): Flow<com.resy.domain.Results<List<PhotoItem>>>
}
