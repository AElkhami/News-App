package com.example.news.discover.domain.usecase

import com.example.news.discover.domain.model.Article
import com.example.news.discover.domain.model.Category

class GetArticleCategoriesUseCase {
    operator fun invoke(articles: List<Article>): List<Category> {
        return articles
            .map(Article::category)
            .distinct()
    }
}