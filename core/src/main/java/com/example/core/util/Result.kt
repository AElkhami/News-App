package com.example.core.util

import com.example.core.error.AppError

/**
 * Represent the result of an operation that can succeed or fail.
 *
 * It enables type safety and expressive error handling
 */
sealed interface Result<out T> {
    data class Success<out T>(val data: T) : Result<T>
    data class Error(val error: AppError) : Result<Nothing>
}