package com.example.core_ui.state

import com.example.core_ui.UiText

sealed interface ScreenState {
    object Loading : ScreenState
    data class Error(val message: UiText) : ScreenState
    object Content : ScreenState
}