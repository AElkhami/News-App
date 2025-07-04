package com.example.news.discover.domain.repository

import com.example.news.core.Result
import com.example.news.discover.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getArticles(): Flow<Result<List<Article>>>
}