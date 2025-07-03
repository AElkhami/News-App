package com.example.news.discover.presentation

import com.example.news.discover.domain.Article

data class DiscoverUiState(
    val articles: List<Article>,
    val categories: List<String>
)
