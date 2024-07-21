package com.resy.photo_details

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.resy.design_system.locals.LocalSizing
import com.resy.design_system.locals.LocalSpacing
import com.resy.design_system.locals.Sizing
import com.resy.design_system.locals.Spacing
import com.resy.models.PhotoItem
import com.resy.photo_details.presentation.ProfileDetailsScreen
import org.junit.Rule
import org.junit.Test

class ProfileDetailsScreenPhotoTest {

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
    fun verifyPhotoDisplaysErrorForInvalidUrl() {
        composeTestRule.setContent {
            CompositionLocalProvider(
                LocalSpacing provides Spacing,
                LocalSizing provides Sizing,
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
    fun verifyPhotoDisplaysCorrectlyForValidUrl() {
        composeTestRule.setContent {
            CompositionLocalProvider(
                LocalSpacing provides Spacing,
                LocalSizing provides Sizing,
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
