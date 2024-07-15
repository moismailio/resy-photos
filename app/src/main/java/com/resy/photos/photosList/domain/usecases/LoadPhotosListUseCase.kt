package com.resy.photos.photosList.domain.usecases

import com.resy.photos.photosList.domain.IPhotosListRepository
import javax.inject.Inject

data class LoadPhotosListUseCase
    @Inject
    constructor(
        private val photosRepository: IPhotosListRepository,
    ) {
        suspend operator fun invoke() = photosRepository.loadPhotos()
    }
