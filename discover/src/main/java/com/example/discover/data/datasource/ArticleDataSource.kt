package com.example.discover.data.datasource

import com.example.core.util.Result
import com.example.discover.domain.model.Article

interface ArticleDataSource {
    suspend fun fetchArticles(): Result<List<Article>>
}