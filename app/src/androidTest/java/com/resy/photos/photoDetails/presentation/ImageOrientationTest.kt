package com.resy.photos.photoDetails.presentation

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.resy.design_system.Sizing
import com.resy.design_system.SizingLocal
import com.resy.design_system.Spacing
import com.resy.design_system.SpacingLocal
import com.resy.photos.photosList.domain.models.PhotoItem
import org.junit.Rule
import org.junit.Test

class ImageOrientationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val photoItem: PhotoItem = PhotoItem(
        id = 1,
        author = "Mo",
        fileName = "name.jpeg",
        width = 5000,
        height = 3333
    )

    private val invalidPhotoItem = photoItem.copy(url = "invalid_url")

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun test_invalid_url_photo() {
        composeTestRule.setContent {
            CompositionLocalProvider(
                SpacingLocal provides Spacing,
                SizingLocal provides Sizing,
            ) {
                ProfileDetailsScreen(
                    photoItem = invalidPhotoItem,
                    onBackClicked = {}
                )
            }
        }

        composeTestRule.onNodeWithTag("author_name").assertIsDisplayed()
        composeTestRule.waitUntilExactlyOneExists(hasTestTag("error_loading"), 3000)
        composeTestRule.onNodeWithTag("error_loading").assertIsDisplayed()
    }

    @Test
    fun test_valid_url_photo() {
        composeTestRule.setContent {
            CompositionLocalProvider(
                SpacingLocal provides Spacing,
                SizingLocal provides Sizing,
            ) {
                ProfileDetailsScreen(
                    photoItem = photoItem,
                    onBackClicked = {}
                )
            }
        }

        composeTestRule.onNodeWithTag("author_name").assertIsDisplayed()
        composeTestRule.onNodeWithTag("photo").assertIsDisplayed()
    }
}
