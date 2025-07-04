package com.example.news.core.error

sealed interface AppError {
    object ParseError : AppError
    object Unknown : AppError
}