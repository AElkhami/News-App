package com.example.news.discover.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.R
import com.example.news.core.ui.UiText
import com.example.news.core.ui.state.ScreenState
import com.example.news.core.util.fold
import com.example.news.discover.domain.model.Article
import com.example.news.discover.domain.model.Category
import com.example.news.discover.domain.usecase.GetArticleCategoriesUseCase
import com.example.news.discover.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val getArticles: GetArticlesUseCase,
    private val getCategories: GetArticleCategoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DiscoverUiState(selectedCategory = Category.ALL.name))
    val uiState: StateFlow<DiscoverUiState> = _uiState.asStateFlow()

    fun loadArticles() {
        viewModelScope.launch {
            _uiState.update { it.copy(screenState = ScreenState.Loading) }

            getArticles().fold(
                onSuccess = { articles ->
                    val categories = buildList {
                        add(Category.ALL)
                        addAll(getCategories(articles))
                    }
                    setArticles(articles, categories)
                },
                onError = { error ->
                    _uiState.update {
                        it.copy(
                            ScreenState.Error(
                                message = UiText.StringResource(R.string.error_unknown)
                            ),
                            articles = emptyList(),
                            categories = emptyList(),
                        )
                    }
                }
            )
        }
    }

    fun showArticlesForCategory(category: String) {
        _uiState.update { state ->
            val filtered = filterArticles(state.articles, category)
            state.copy(
                selectedCategory = category,
                filteredArticles = filtered
            )
        }
    }

    private fun filterArticles(articles: List<Article>, category: String): List<Article> {
        return if (category == Category.ALL.name) {
            articles
        } else {
            articles.filter { it.category.name == category }
        }
    }

    private fun setArticles(articles: List<Article>, categories: List<Category>) {
        _uiState.update {
            it.copy(
                screenState = ScreenState.Content,
                articles = articles,
                filteredArticles = articles,
                categories = categories
            )
        }
    }
}