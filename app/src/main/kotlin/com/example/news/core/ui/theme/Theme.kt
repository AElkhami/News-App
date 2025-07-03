package com.example.news.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkAppColors else LightAppColors
    val dimens = DefaultAppDimens
    val typography = DefaultAppTypography

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppDimens provides dimens,
        LocalAppTypography provides typography
    ) {
        MaterialTheme(
            colorScheme = if (useDarkTheme) darkColorScheme(
                primary = colors.primary,
                background = colors.background,
                surface = colors.surface,
                onBackground = colors.textPrimary,
                onSurface = colors.textPrimary
            ) else lightColorScheme(
                primary = colors.primary,
                background = colors.background,
                surface = colors.surface,
                onBackground = colors.textPrimary,
                onSurface = colors.textPrimary
            ),
            shapes = Shapes(
                small = RoundedCornerShape(dimens.cardCornerRadius),
                medium = RoundedCornerShape(dimens.cardCornerRadius),
                large = RoundedCornerShape(dimens.none)
            ),
            content = content
        )
    }
}


