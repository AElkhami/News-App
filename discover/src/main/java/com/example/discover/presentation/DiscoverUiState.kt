package com.example.discover.presentation

import com.example.core_ui.state.ScreenState
import com.example.discover.domain.model.Article
import com.example.discover.domain.model.Category

data class DiscoverUiState(
    val screenState: ScreenState = ScreenState.Loading,
    val articles: List<Article> = emptyList(),
    val filteredArticles: List<Article> = emptyList(),
    val categories: List<Category> = emptyList(),
    val selectedCategory: String = Category.ALL.name
)
