package com.example.core_ui.state

import com.example.core_ui.UiText

/**
 * This sealed interface defines the possible high-level states a screen can be.
 */
sealed interface ScreenState<out T> {
    object Loading : ScreenState<Nothing>
    data class Error(val message: UiText) : ScreenState<Nothing>
    data class Content<out T>(val data: T) : ScreenState<T>
}