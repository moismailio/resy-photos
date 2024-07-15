package com.resy.photos.photosList.data

import retrofit2.Response
import retrofit2.http.GET

interface PhotoListApi {
    @GET("/list")
    suspend fun loadPhotosList(): Response<List<PhotoDto>>
}
