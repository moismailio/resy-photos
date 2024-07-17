package com.resy.design_system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

// TODO : clean a bit
object Sizing {
    val scaleXS = 16.dp
    val scaleS = 20.dp
    val scaleM = 24.dp
    val scaleL = 32.dp
    val scaleXL = 40.dp
    val scaleXXL = 48.dp
    val scaleXXXL = 56.dp
}

internal val SizingLocal = compositionLocalOf<Sizing> { error("sizing not provided") }

val sizing
    @Composable get() = SizingLocal.current
