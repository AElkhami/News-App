package com.example.news

import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    val articles: List<Article>
)
