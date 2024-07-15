package com.resy.photos.photoDetails.presentation

import com.resy.photos.photosList.domain.models.PhotoItem
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class ImageOrientationTest {

    private val landscapeImage: PhotoItem = mockk(relaxed = true) {
        every { width } returns 5000
        every { height } returns 3333
    }

    private val portraitImage: PhotoItem = mockk(relaxed = true) {
        every { width } returns 3333
        every { height } returns 5000
    }

    @Test
    fun validate_landscape_image() {
        assert(!landscapeImage.isPortraitImage)
    }

    @Test
    fun validate_portrait_image() {
        assert(portraitImage.isPortraitImage)
    }
}