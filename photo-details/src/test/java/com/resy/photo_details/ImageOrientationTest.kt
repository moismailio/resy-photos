package com.resy.photo_details

import com.resy.photo_details.presentation.isPortraitImage
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class ImageOrientationTest {

    private val landscapeImage: com.resy.models.PhotoItem = mockk(relaxed = true) {
        every { width } returns 5000
        every { height } returns 3333
    }

    private val portraitImage: com.resy.models.PhotoItem = mockk(relaxed = true) {
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