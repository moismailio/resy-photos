package com.resy.photos.photosList.domain.models

import android.os.Parcelable
import com.resy.photos.BuildConfig
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoItem(
    val id: Long,
    val author: String,
    val fileName: String,
    val width: Int,
    val height: Int,
    val url: String = "${BuildConfig.HOST_URL}/$width/$height?image=$id",
) : Parcelable
