package com.example.discover.data.repository

import com.example.core.util.Result
import com.example.discover.data.datasource.ArticleDataSource
import com.example.discover.domain.model.Article
import com.example.discover.domain.repository.ArticleRepository
import javax.inject.Inject

class AssetsArticleRepository @Inject constructor(
    private val dataSource: ArticleDataSource,
) : ArticleRepository {

    override suspend fun getArticles(): Result<List<Article>>  {
        return dataSource.fetchArticles()
    }
}