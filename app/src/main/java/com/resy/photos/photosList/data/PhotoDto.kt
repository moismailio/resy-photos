package com.resy.photos.photosList.data

data class PhotoDto(
    val id: Long,
    val author: String,
    val width: Int,
    val height: Int,
    val filename: String,
)
