package com.example.discover.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    val articles: List<ArticleDto>
)
