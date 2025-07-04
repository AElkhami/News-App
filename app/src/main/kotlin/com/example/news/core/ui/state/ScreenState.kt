package com.example.news.core.ui.state

import com.example.news.core.ui.UiText

sealed interface ScreenState {
    object Loading : ScreenState
    data class Error(val message: UiText) : ScreenState
    object Content : ScreenState
}