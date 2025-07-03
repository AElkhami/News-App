package com.example.news.discover.data.repository

import com.example.news.core.di.qualifier.ApplicationScope
import com.example.news.discover.domain.ArticleDataSource
import com.example.news.discover.domain.model.Article
import com.example.news.discover.domain.repository.ArticleRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class AssetsArticleRepository @Inject constructor(
    private val dataSource: ArticleDataSource,
    @ApplicationScope coroutineScope: CoroutineScope
) : ArticleRepository {

    private val articlesStateFlow: StateFlow<List<Article>> =
        flow {
            emit(dataSource.fetchArticles())
        }
            .stateIn(
                coroutineScope,
                started = SharingStarted.Lazily,
                initialValue = emptyList()
            )

    override fun getArticles(): Flow<List<Article>> = articlesStateFlow
}