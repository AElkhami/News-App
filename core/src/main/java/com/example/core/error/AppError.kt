package com.example.core.error

sealed interface AppError {
    object ParseError : AppError
    object FileNotFound : AppError
    object InvalidData : AppError
    object Unknown : AppError
}