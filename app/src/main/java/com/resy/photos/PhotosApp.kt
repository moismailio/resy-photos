package com.resy.photos

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class PhotosApp : Application(), ImageLoaderFactory {

    @Inject
    lateinit var imageloader: ImageLoader

    override fun newImageLoader(): ImageLoader = imageloader
}