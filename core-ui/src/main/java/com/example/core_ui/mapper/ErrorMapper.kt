package com.example.core_ui.mapper

import com.example.core.error.AppError
import com.example.core_ui.R
import com.example.core_ui.UiText
import com.example.core_ui.UiText.StringResource

fun AppError.toUiText(): UiText {
    return when (this) {
        AppError.ParseError -> StringResource(R.string.error_parse)
        AppError.FileNotFound -> StringResource(R.string.error_parse)
        AppError.InvalidData -> StringResource(R.string.error_parse)
        is AppError.Unknown -> StringResource(R.string.error_unknown)
    }
}