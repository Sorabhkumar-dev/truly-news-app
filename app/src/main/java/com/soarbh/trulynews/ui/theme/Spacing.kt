package com.soarbh.trulynews.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Spacing {
    val space1:Dp = 1.dp
    val space2:Dp = 2.dp
    val space3:Dp = 3.dp
    val space4:Dp = 4.dp
    val space5:Dp = 5.dp
    val space6:Dp = 6.dp
    val space8:Dp = 8.dp
    val space12:Dp = 12.dp
    val space16:Dp = 16.dp
    val space24:Dp = 24.dp
    val space48:Dp = 48.dp
    val space128:Dp = 128.dp
    val space150:Dp = 150.dp
    val space100:Dp = 100.dp
    val space300:Dp = 300.dp
    val space500:Dp = 500.dp
}

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current