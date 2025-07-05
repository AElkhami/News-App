package com.example.core.util

import com.example.core.error.AppError

/**
 * Applies the appropriate function to the [Result],
 * depending on whether it is [Result.Success] or [Result.Error].
 *
 * integrating [AppError] to ensure type safety and expressive error handling.
 */
inline fun <T, R> Result<T>.fold(
    onSuccess: (T) -> R,
    onError: (AppError) -> R
): R = when (this) {
    is Result.Success -> onSuccess(data)
    is Result.Error -> onError(error)
}