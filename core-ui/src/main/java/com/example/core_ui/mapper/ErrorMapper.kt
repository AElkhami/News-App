package com.example.core_ui.mapper

import com.example.core.error.AppError
import com.example.core_ui.R
import com.example.core_ui.UiText
import com.example.core_ui.UiText.StringResource

/**
 * Maps an [AppError] to a user-facing [UiText], providing localized error messages.
 *
 * This extension function enables UI layers to display meaningful error messages
 * without leaking error implementation details.
 */
fun AppError.toUiText(): UiText {
    return when (this) {
        AppError.ParseError -> StringResource(R.string.error_parse)
        AppError.FileNotFound -> StringResource(R.string.error_parse)
        AppError.InvalidData -> StringResource(R.string.error_parse)
        is AppError.Unknown -> StringResource(R.string.error_unknown)
    }
}