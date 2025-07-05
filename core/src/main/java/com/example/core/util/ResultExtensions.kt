package com.example.core.util

import com.example.core.error.AppError

inline fun <T, R> Result<T>.fold(
    onSuccess: (T) -> R,
    onError: (AppError) -> R
): R = when (this) {
    is Result.Success -> onSuccess(data)
    is Result.Error -> onError(error)
}