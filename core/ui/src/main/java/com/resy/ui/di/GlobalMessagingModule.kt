package com.resy.ui.di

import com.resy.ui.GlobalMessagingImpl
import com.resy.ui.GlobalMessagingReceiver
import com.resy.ui.GlobalMessagingSender
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GlobalMessagingModule {

    @Binds
    @Singleton
    abstract fun bindsGlobalMessagingReceiver(globalMessagingImpl: GlobalMessagingImpl) : GlobalMessagingReceiver

    @Binds
    @Singleton
    abstract fun bindsGlobalMessagingSender(globalMessagingImpl: GlobalMessagingImpl) : GlobalMessagingSender
}