package com.resy.photo_list_domain.usecases

import com.resy.photo_list_domain.IPhotosListRepository
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

data class LoadPhotosListUseCase @Inject constructor(
    private val photosRepository: IPhotosListRepository,
) {
    suspend operator fun invoke() = photosRepository.loadPhotos()
}
