package com.resy.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun calculateHeightFromAspectRatioInDp(width: Float, height: Float): Dp {
    val aspectRatio = width / height
    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels
    val calculatedHeight = screenWidth / aspectRatio
    return calculatedHeight.toDp()
}

@Composable
fun Float.toDp() =
    with(LocalDensity.current) {
        this@toDp.toDp()
    }
