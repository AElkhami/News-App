package com.example.news.discover.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.R
import com.example.news.core.ui.UiText
import com.example.news.core.util.fold
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
): ViewModel() {
    private val _uiState = MutableStateFlow(DiscoverUiState())
    val uiState: StateFlow<DiscoverUiState> = _uiState.asStateFlow()

    fun loadArticles() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            getArticles().fold(
                onSuccess = { articles ->
                    val categories = getCategories(articles)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            articles = articles,
                            categories = categories,
                            errorMessage = null
                        )
                    }
                },
                onError = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            articles = emptyList(),
                            categories = emptyList(),
                            errorMessage = UiText.StringResource(R.string.error_unknown)
                        )
                    }
                }
            )
        }
    }
}