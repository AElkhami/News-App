package com.example.discover.data.datasource

import android.content.res.AssetManager
import com.example.core.error.AppError
import com.example.discover.data.di.qualifier.ResponseAssetName
import com.example.discover.domain.datasource.ArticleDataSource
import com.example.discover.domain.model.Article
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import com.example.core.util.Result
import com.example.discover.data.mapper.toDomain
import com.example.discover.data.model.ArticleDto
import com.example.discover.data.model.ArticleResponse
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.withContext
import timber.log.Timber

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

                Timber.d("Raw JSON loaded: ${raw.length} characters")

                val articles = json.decodeFromString(ArticleResponse.serializer(), raw)
                    .articles
                    .map(ArticleDto::toDomain)

                Result.Success(articles)
            }.getOrElse { throwable ->
                when (throwable) {
                    is CancellationException -> throw throwable
                    is java.io.FileNotFoundException -> {
                        Timber.e("Asset file not found: $assetName")
                        Result.Error(AppError.FileNotFound)
                    }

                    is kotlinx.serialization.SerializationException -> {
                        Timber.e("JSON parsing failed: ${throwable.message}")
                        Result.Error(AppError.ParseError)
                    }

                    is IllegalArgumentException -> {
                        Timber.e("Invalid file: ${throwable.message}")
                        Result.Error(AppError.InvalidData)
                    }

                    else -> {
                        Timber.e("Unknown error: ${throwable.message}")
                        Result.Error(AppError.Unknown)
                    }
                }
            }
        }
    }
}