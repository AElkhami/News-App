package com.example.discover.domain.model

data class Article(
    val category: Category,
    val title: String,
    val description: String,
    val headerImageURL: String
)