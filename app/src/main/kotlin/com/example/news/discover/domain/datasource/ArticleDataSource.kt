package com.example.news.discover.domain.datasource

import com.example.news.core.util.Result
import com.example.news.discover.domain.model.Article

interface ArticleDataSource {
    suspend fun fetchArticles(): Result<List<Article>>
}