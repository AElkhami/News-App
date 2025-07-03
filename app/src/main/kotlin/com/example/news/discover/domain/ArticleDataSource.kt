package com.example.news.discover.domain

import com.example.news.discover.domain.model.Article

interface ArticleDataSource {
    suspend fun fetchArticles(): List<Article>
}