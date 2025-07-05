package com.example.core_ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColors(
    val primary: Color,
    val background: Color,
    val surface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val divider: Color,
    val error: Color
)

private val LightError = Color(0xFFB3261E)
private val DarkError = Color(0xFFF2B8B5)
private val LightTextSecondary = Color(0xFF5A6268)

val LightAppColors = AppColors(
    primary = Color(0xFF007BFF),
    background = Color.White,
    surface = Color(0xFFF7F7F7),
    textPrimary = Color(0xFF292D31),
    textSecondary = LightTextSecondary,
    divider = Color(0xFF000000).copy(alpha = 0.12f),
    error = LightError
)

val DarkAppColors = AppColors(
    primary = Color(0xFF64B5F6),
    background = Color(0xFF1E1E1E),
    surface = Color(0xFF121212),
    textPrimary = Color.White,
    textSecondary = Color(0xFFB0BEC5),
    divider = Color.White.copy(alpha = 0.12f),
    error = DarkError
)

val LocalAppColors = staticCompositionLocalOf { LightAppColors }
