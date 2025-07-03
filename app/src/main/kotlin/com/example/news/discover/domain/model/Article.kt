package com.example.news.discover.domain.model

data class Article(
    val category: String,
    val title: String,
    val description: String,
    val headerImageURL: String
)