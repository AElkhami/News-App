package com.example.news.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

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
            colorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (useDarkTheme) dynamicDarkColorScheme(LocalContext.current)
                else dynamicLightColorScheme(LocalContext.current)
            } else {
                if (useDarkTheme) darkColorScheme() else lightColorScheme()
            },
            content = content
        )
    }
}


