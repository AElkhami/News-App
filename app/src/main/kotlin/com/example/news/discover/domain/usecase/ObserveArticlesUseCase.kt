package com.example.news.discover.domain.usecase

import com.example.news.core.Result
import com.example.news.discover.domain.model.Article
import com.example.news.discover.domain.repository.ArticleRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveArticlesUseCase @Inject constructor(
    private val repository: ArticleRepository
) {
    operator fun invoke(): Flow<Result<List<Article>>> = repository.getArticles()
}