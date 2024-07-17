package com.resy.design_system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

// TODO : clean a bit
object Spacing {
    val spaceXXXXS = 2.dp
    val spaceXXXS = 4.dp
    val spaceXXS = 8.dp
    val spaceXS = 12.dp
    val spaceS = 16.dp
    val spaceM = 20.dp
    val spaceL = 24.dp
    val spaceXL = 32.dp
    val spaceXXL = 40.dp
    val spaceXXXL = 48.dp
}

internal val SpacingLocal = compositionLocalOf<Spacing> { error("spacing not provided") }

val spacing
    @Composable get() = SpacingLocal.current
