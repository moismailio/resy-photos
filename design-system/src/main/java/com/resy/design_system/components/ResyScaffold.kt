package com.resy.design_system.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.resy.design_system.R
import com.resy.design_system.locals.Spacing
import com.resy.design_system.utils.colors

@Composable
fun ResyScaffold(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
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
        modifier = Modifier
            .clickable(onClick = onIconClicked)
            .padding(Spacing.spaceS),
        imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
        contentDescription = stringResource(R.string.back_text),
    )
}
