package com.resy.photos.photosList.di

import com.resy.photos.photosList.data.PhotoListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhotoListApiModule {
    @Provides
    @Singleton
    fun providePhotoListApi(retrofit: Retrofit): PhotoListApi = retrofit.create<PhotoListApi>()
}
