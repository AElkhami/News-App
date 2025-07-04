package com.example.news.discover.data.di

import android.content.Context
import com.example.news.discover.data.datasource.AssetArticleDataSource
import com.example.news.discover.data.di.qualifier.ResponseAssetName
import com.example.news.discover.data.repository.AssetsArticleRepository
import com.example.news.discover.domain.datasource.ArticleDataSource
import com.example.news.discover.domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    @ResponseAssetName
    fun provideResponseAssetName(): String = "api_response.json"

    @Provides
    @Singleton
    fun provideArticleDataSource(
        @ApplicationContext
        context: Context,
        json: Json,
        @ResponseAssetName assetName: String
    ): ArticleDataSource =
        AssetArticleDataSource(
            assets = context.assets,
            json = json,
            dispatcher = Dispatchers.IO,
            assetName = assetName
        )

    @Provides
    @Singleton
    fun provideArticleRepository(
        dataSource: ArticleDataSource,
    ): ArticleRepository = AssetsArticleRepository(dataSource)
}