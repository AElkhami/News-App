package com.example.discover.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.util.fold
import com.example.core_ui.mapper.toUiText
import com.example.core_ui.state.ScreenState
import com.example.discover.domain.model.Article
import com.example.discover.domain.model.Category
import com.example.discover.domain.usecase.GetArticleCategoriesUseCase
import com.example.discover.domain.usecase.GetArticlesUseCase
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

    /**
     * Loads all articles and their categories.
     *
     * Updates the [uiState] to [ScreenState.Loading] while loading, and then to
     * either [ScreenState.Content] on success or [ScreenState.Error] on failure.
     */
    fun loadArticles() {
        if (_uiState.value.screenState == ScreenState.Loading) {
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
                                screenState = ScreenState.Error(
                                    message = error.toUiText()
                                ),
                                categories = emptyList(),
                                filteredArticles = emptyList()
                            )
                        }
                    }
                )
            }
        }
    }

    /**
     * Updates the [uiState] to show articles filtered by the selected [category].
     *
     * @param category The name of the selected category.
     */
    fun showArticlesForCategory(category: String) {
        _uiState.update { state ->
            val normalizedCategory = if (state.categories.any { it.name == category }) category else Category.ALL.name
            val articles = (state.screenState as? ScreenState.Content<List<Article>>)?.data
                ?: return@update state.copy(
                    selectedCategory = normalizedCategory,
                    filteredArticles = emptyList()
                )
            state.copy(
                selectedCategory = normalizedCategory,
                filteredArticles = filterArticles(articles, normalizedCategory)
            )
        }
    }

    /**
     * Filters the given list of [articles] based on the specified [category].
     *
     * If the [category] is [Category.ALL], returns all articles.
     *
     * @param articles The list of articles to filter.
     * @param category The category to filter by.
     * @return A filtered list of articles.
     */
    private fun filterArticles(articles: List<Article>, category: String): List<Article> {
        return if (category == Category.ALL.name) {
            articles
        } else {
            articles.filter { it.category.name == category }
        }
    }

    /**
     * Updates the [uiState] with the loaded articles and categories,
     * setting the screen state to [ScreenState.Content].
     *
     * @param articles The list of loaded articles.
     * @param categories The list of available categories.
     */
    private fun setArticles(articles: List<Article>, categories: List<Category>) {
        _uiState.update {
            it.copy(
                screenState = ScreenState.Content(articles),
                filteredArticles = articles,
                categories = categories
            )
        }
    }
}