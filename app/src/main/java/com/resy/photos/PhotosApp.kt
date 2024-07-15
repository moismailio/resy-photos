package com.resy.photos

import android.app.Application
import com.resy.photos.core.util.ImageLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PhotosApp : Application() {
    init {
        ImageLoader(this)
    }
}
