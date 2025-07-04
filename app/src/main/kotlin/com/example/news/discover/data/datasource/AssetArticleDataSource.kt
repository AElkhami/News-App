package com.example.news.discover.data.datasource

import android.content.res.AssetManager
import com.example.news.core.error.AppError
import com.example.news.core.Result
import com.example.news.discover.data.di.qualifier.ResponseAssetName
import com.example.news.discover.data.mapper.toDomain
import com.example.news.discover.data.model.ArticleDto
import com.example.news.discover.data.model.ArticleResponse
import com.example.news.discover.domain.datasource.ArticleDataSource
import com.example.news.discover.domain.model.Article
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class AssetArticleDataSource @Inject constructor(
    private val assets: AssetManager,
    private val json: Json,
    private val dispatcher: CoroutineDispatcher,
    @param:ResponseAssetName private val assetName: String
) : ArticleDataSource {
    override suspend fun fetchArticles(): Result<List<Article>> {
        return withContext(dispatcher) {
            runCatching {
                val raw = assets.open(assetName).bufferedReader().use { it.readText() }
                val articles = json.decodeFromString(ArticleResponse.serializer(), raw)
                    .articles
                    .map(ArticleDto::toDomain)

                Result.Success(articles)
            }.getOrElse { throwable ->
                if (throwable is CancellationException) throw throwable
                Result.Error(AppError.ParseError)
            }
        }
    }
}