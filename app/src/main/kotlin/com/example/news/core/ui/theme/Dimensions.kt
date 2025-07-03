package com.example.news.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class AppDimens(
    val none: Dp = 0.dp,
    val xSmallPadding: Dp = 4.dp,
    val smallPadding: Dp = 8.dp,
    val mediumPadding: Dp = 16.dp,
    val cardCornerRadius: Dp = 12.dp,
    val cardElevation: Dp = 4.dp,
    val cardSpacing: Dp = 12.dp,
    val tabHeight: Dp = 40.dp,
    val tabCornerRadius: Dp = 20.dp,
    val articleImageSize: Dp = 100.dp,
    val articleComposableHeigh: Dp = 100.dp
)

val DefaultAppDimens = AppDimens()

val LocalAppDimens = staticCompositionLocalOf { DefaultAppDimens }
