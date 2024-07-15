package com.resy.photos.photosList.domain.mappers

import com.resy.photos.core.domain.Mapper
import com.resy.photos.photosList.data.PhotoDto
import com.resy.photos.photosList.domain.models.PhotoItem
import javax.inject.Inject

class PhotoItemMapper
    @Inject
    constructor() : Mapper<PhotoDto, PhotoItem> {
        override fun map(input: PhotoDto): PhotoItem =
            PhotoItem(
                id = input.id,
                author = input.author,
                fileName = input.filename,
                width = input.width,
                height = input.height,
            )
    }
