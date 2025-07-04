package com.example.news.core

import com.example.news.core.error.AppError

sealed interface Result<out T> {
    data class Success<out T>(val data: T) : Result<T>
    data class Error(val error: AppError) : Result<Nothing>
}

fun <T, R> Result<T>.map(transform: (T) -> R): Result<R> = when (this) {
    is Result.Success -> Result.Success(transform(data))
    is Result.Error -> this
}