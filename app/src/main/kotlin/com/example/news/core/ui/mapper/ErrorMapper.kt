package com.example.news.core.ui.mapper

import com.example.news.R
import com.example.news.core.error.AppError
import com.example.news.core.ui.UiText

fun AppError.toUiText(): UiText {
    return when (this) {
        AppError.ParseError -> UiText.StringResource(R.string.error_parse)
        is AppError.Unknown -> UiText.StringResource(R.string.error_unknown)
    }
}