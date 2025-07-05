package com.example.discover.domain.usecase

import com.example.discover.domain.model.Article
import com.example.discover.domain.model.Category

class GetArticleCategoriesUseCase {
    operator fun invoke(articles: List<Article>): List<Category> {
        return articles
            .map(Article::category)
            .distinct()
    }
}