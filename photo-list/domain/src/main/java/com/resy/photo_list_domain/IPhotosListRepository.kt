package com.resy.photo_list_domain

import com.resy.domain.Results
import com.resy.models.PhotoItem
import kotlinx.coroutines.flow.Flow

interface IPhotosListRepository {
    suspend fun loadPhotos(): Flow<Results<List<PhotoItem>>>
}
