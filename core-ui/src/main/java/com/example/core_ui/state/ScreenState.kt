package com.example.core_ui.state

import com.example.core_ui.UiText

/**
 * This sealed interface defines the possible high-level states a screen can be.
 */
sealed interface ScreenState {
    object Loading : ScreenState
    data class Error(val message: UiText) : ScreenState
    object Content : ScreenState
}