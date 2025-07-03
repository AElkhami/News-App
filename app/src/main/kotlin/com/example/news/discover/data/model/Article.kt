package com.example.news.discover.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val type: String,
    val title: String,
    val description: String,
    val headerImageURL: String
)
