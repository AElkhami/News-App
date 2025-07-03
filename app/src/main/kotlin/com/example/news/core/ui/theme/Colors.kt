package com.example.news.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColors(
    val primary: Color,
    val background: Color,
    val surface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val divider: Color
)

val LightAppColors = AppColors(
    primary = Color(0xFF007BFF),
    background = Color(0xFFF9F9F9),
    surface = Color.White,
    textPrimary = Color(0xFF000000),
    textSecondary = Color(0xFF6C757D),
    divider = Color(0xFFE0E0E0)
)

val DarkAppColors = AppColors(
    primary = Color(0xFF64B5F6),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    textPrimary = Color(0xFFFFFFFF),
    textSecondary = Color(0xFFB0BEC5),
    divider = Color(0xFF37474F)
)


val LocalAppColors = staticCompositionLocalOf { LightAppColors }
