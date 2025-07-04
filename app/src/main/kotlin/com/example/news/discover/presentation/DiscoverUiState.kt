package com.example.news.discover.presentation

import com.example.news.core.ui.UiText
import com.example.news.discover.domain.model.Article
import com.example.news.discover.domain.model.Category

data class DiscoverUiState(
    val articles: List<Article> = emptyList(),
    val filteredArticles: List<Article> = emptyList(),
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
    val selectedCategory: String = Category.ALL.name
)
