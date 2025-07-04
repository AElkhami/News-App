package com.example.news.core.util

import com.example.news.core.error.AppError

sealed interface Result<out T> {
    data class Success<out T>(val data: T) : Result<T>
    data class Error(val error: AppError) : Result<Nothing>
}