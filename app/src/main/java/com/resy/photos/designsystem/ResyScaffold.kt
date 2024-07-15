package com.resy.photos.designsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ResyScaffold(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        topBar = header,
    ) { padding ->
        content(padding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResyTopBar(
    title: String,
    navigationIcon: @Composable () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title, color = colors.primary)
        },
        navigationIcon = navigationIcon,
    )
}

@Composable
fun TopBarBackIcon(onIconClicked: () -> Unit) {
    Icon(
        modifier = Modifier.clickable(onClick = onIconClicked).padding(spacing.spaceS),
        imageVector = Icons.Default.KeyboardArrowLeft,
        contentDescription = "back",
    )
}
