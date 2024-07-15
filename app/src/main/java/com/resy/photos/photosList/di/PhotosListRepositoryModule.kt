package com.resy.photos.photosList.di

import com.resy.photos.photosList.data.PhotosListRepositoryImplementation
import com.resy.photos.photosList.domain.IPhotosListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PhotosListRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindPhotosListRepository(repository: PhotosListRepositoryImplementation): IPhotosListRepository
}
