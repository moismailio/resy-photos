package com.resy.design_system.locals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

object Spacing {
    val spaceXXS = 8.dp
    val spaceS = 16.dp
}

val LocalSpacing = compositionLocalOf<Spacing> { error("spacing not provided") }

val spacing
    @Composable get() = LocalSpacing.current
