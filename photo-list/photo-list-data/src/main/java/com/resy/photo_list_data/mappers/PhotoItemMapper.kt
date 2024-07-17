package com.resy.photo_list_data.mappers

import com.resy.domain.Mapper
import com.resy.models.PhotoItem
import com.resy.photo_list_data.PhotoDto
import javax.inject.Inject

class PhotoItemMapper
    @Inject
    constructor() : Mapper<PhotoDto, PhotoItem> {
        override fun map(input:PhotoDto): PhotoItem =
            PhotoItem(
                id = input.id,
                author = input.author,
                fileName = input.filename,
                width = input.width,
                height = input.height,
            )
    }
