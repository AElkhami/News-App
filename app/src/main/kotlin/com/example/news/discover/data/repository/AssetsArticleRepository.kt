package com.example.news.discover.data.repository

import com.example.news.core.util.Result
import com.example.news.discover.domain.datasource.ArticleDataSource
import com.example.news.discover.domain.model.Article
import com.example.news.discover.domain.repository.ArticleRepository
import javax.inject.Inject

class AssetsArticleRepository @Inject constructor(
    private val dataSource: ArticleDataSource,
) : ArticleRepository {

    override suspend fun getArticles(): Result<List<Article>>  {
        return dataSource.fetchArticles()
    }
}