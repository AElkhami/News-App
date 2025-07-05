package com.example.discover.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    val type: String,
    val title: String,
    val description: String,
    val headerImageURL: String
)
