package com.resy.photo_list_data.di

import com.resy.photo_list_data.PhotosListRepositoryImplementation
import com.resy.photo_list_domain.IPhotosListRepository
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
