package com.example.news.discover.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.discover.domain.usecase.ObserveArticleCategoriesUseCase
import com.example.news.discover.domain.usecase.ObserveArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val observeArticles: ObserveArticlesUseCase,
    private val observeCategories: ObserveArticleCategoriesUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(DiscoverUiState())
    val uiState: StateFlow<DiscoverUiState> = _uiState.asStateFlow()

    fun loadArticles() {
        viewModelScope.launch {
            combine(
                observeArticles(),          // Flow<List<Article>>
                observeCategories()         // Flow<List<Category>>
            ) { articles, categories ->
                // on each emission, build a new UI state
                DiscoverUiState(
                    isLoading   = false,
                    articles    = articles,
                    categories  = categories
                )
            }
                .onStart {
                    // before first emission, show loading
                    _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                }
                .catch { throwable ->
                    // if upstream throws, emit error state
                    _uiState.update {
                        it.copy(
                            isLoading   = false,
                            errorMessage = throwable.localizedMessage
                        )
                    }
                }
                .collect { newState ->
                    // collect your combined state
                    _uiState.value = newState
                }
        }
    }
}