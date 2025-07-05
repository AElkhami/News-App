package com.example.core_ui.compositionlocal

import androidx.compose.runtime.staticCompositionLocalOf
import coil3.ImageLoader

/**
 * A CompositionLocal that provides an [ImageLoader] instance to the Compose UI tree.
 *
 * This allows UI components and screens to access a shared, configured [ImageLoader]
 * without explicitly passing it down through every composable.
 */
val LocalImageLoader = staticCompositionLocalOf<ImageLoader> {
    error("No ImageLoader provided. Did you forget to wrap your UI with a CompositionLocalProvider?")
}