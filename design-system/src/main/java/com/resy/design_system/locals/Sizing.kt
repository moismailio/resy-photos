package com.resy.design_system.locals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

object Sizing {
    val scaleXXXL = 56.dp
}

val LocalSizing = compositionLocalOf<Sizing> { error("sizing not provided") }

val sizing
    @Composable get() = LocalSizing.current
