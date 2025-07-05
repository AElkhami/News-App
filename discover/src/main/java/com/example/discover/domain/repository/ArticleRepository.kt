package com.example.discover.domain.repository

import com.example.core.util.Result
import com.example.discover.domain.model.Article

interface ArticleRepository {
    suspend fun getArticles(): Result<List<Article>>
}