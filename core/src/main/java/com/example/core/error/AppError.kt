package com.example.core.error

/**
 * Represents a domain-level error, that can occur when working with data sources
 *
 * it defines possible error types that can occur in the app and allows
 * strongly typed error handling
 */
sealed interface AppError {
    object ParseError : AppError
    object FileNotFound : AppError
    object InvalidData : AppError
    object Unknown : AppError
}