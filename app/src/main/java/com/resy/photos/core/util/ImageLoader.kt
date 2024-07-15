package com.resy.photos.core.util

import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger

class ImageLoader(
    val context: Context,
) : ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader =
        ImageLoader(context)
            .newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache
                    .Builder(context)
                    .maxSizePercent(0.05)
                    .strongReferencesEnabled(true)
                    .build()
            }.diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache
                    .Builder()
                    .maxSizePercent(0.03)
                    .directory(context.cacheDir)
                    .build()
            }.logger(DebugLogger())
            .build()
}
