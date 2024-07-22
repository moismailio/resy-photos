package com.resy.photo_details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.resy.design_system.components.ResyScaffold
import com.resy.design_system.components.ResyTopBar
import com.resy.design_system.components.TopBarBackIcon
import com.resy.design_system.components.UrlImage
import com.resy.design_system.locals.spacing
import com.resy.design_system.theme.Typography
import com.resy.design_system.utils.colors
import com.resy.models.PhotoItem
import com.resy.photo_details.R
import com.resy.ui.calculateHeightFromAspectRatioInDp

@Composable
internal fun ProfileDetailsScreen(
    photoItem: PhotoItem,
    onBackClicked: () -> Unit,
) {
    ResyScaffold(
        header = {
            ResyTopBar(
                title = stringResource(R.string.photo_details_screen_title),
                navigationIcon = { TopBarBackIcon(onIconClicked = onBackClicked) },
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
            Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = if (photoItem.isPortraitImage) Arrangement.Top else Arrangement.Center,
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart) {
                UrlImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(photoItem.relativeImageHeight),
                    url = photoItem.url,
                    fileName = photoItem.fileName
                )
                Text(
                    modifier = Modifier
                        .padding(spacing.spaceS)
                        .background(
                            color = colors.onSurface,
                            shape = RoundedCornerShape(spacing.spaceXXS)
                        )
                        .padding(spacing.spaceXXS),
                    text = photoItem.fileName,
                    color = colors.surface,
                    style = Typography.headlineSmall,
                )
            }
            Text(
                modifier = Modifier
                    .testTag("author_name")
                    .padding(top = spacing.spaceXXS, start = spacing.spaceXXS),
                text = photoItem.author,
                style = Typography.bodyLarge,
            )
        }
    }
}

private val PhotoItem.relativeImageHeight
    @Composable get() =
        calculateHeightFromAspectRatioInDp(width = width.toFloat(), height = height.toFloat())

internal val PhotoItem.isPortraitImage
    get() = height > width

