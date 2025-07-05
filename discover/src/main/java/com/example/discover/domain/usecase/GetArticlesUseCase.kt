package com.example.discover.domain.usecase

import com.example.core.util.Result
import com.example.discover.domain.model.Article
import com.example.discover.domain.repository.ArticleRepository
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(): Result<List<Article>> = repository.getArticles()
}