package com.example.news.discover.domain.model

data class Article(
    val category: Category,
    val title: String,
    val description: String,
    val headerImageURL: String
)