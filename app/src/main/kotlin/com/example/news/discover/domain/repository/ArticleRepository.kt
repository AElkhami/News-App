package com.example.news.discover.domain.repository

import com.example.news.core.util.Result
import com.example.news.discover.domain.model.Article

interface ArticleRepository {
    suspend fun getArticles(): Result<List<Article>>
}