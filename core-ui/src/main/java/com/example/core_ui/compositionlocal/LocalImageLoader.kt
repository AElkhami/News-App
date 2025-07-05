package com.example.core_ui.compositionlocal

import androidx.compose.runtime.staticCompositionLocalOf
import coil3.ImageLoader

val LocalImageLoader = staticCompositionLocalOf<ImageLoader> {
    error("No ImageLoader provided. Did you forget to wrap your UI with a CompositionLocalProvider?")
}