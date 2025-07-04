package com.example.news.discover.domain.usecase

import com.example.news.core.util.Result
import com.example.news.discover.domain.model.Article
import com.example.news.discover.domain.repository.ArticleRepository
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(): Result<List<Article>> = repository.getArticles()
}