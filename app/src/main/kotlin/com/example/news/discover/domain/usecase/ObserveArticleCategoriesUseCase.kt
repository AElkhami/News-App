package com.example.news.discover.domain.usecase

import com.example.news.discover.domain.model.Article
import com.example.news.discover.domain.model.Category
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveArticleCategoriesUseCase @Inject constructor(
    private val observeArticles: ObserveArticlesUseCase
) {
    operator fun invoke(): Flow<List<Category>> =
        observeArticles().map { list ->
            list.map(Article::category).distinct().map(::Category)
        }
}