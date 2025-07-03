package com.example.news.discover.domain.di

import com.example.news.discover.domain.repository.ArticleRepository
import com.example.news.discover.domain.usecase.ObserveArticleCategoriesUseCase
import com.example.news.discover.domain.usecase.ObserveArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
object DomainModule {
    @Provides
    @ViewModelScoped
    fun provideObserveArticlesUseCase(repository: ArticleRepository)
            : ObserveArticlesUseCase = ObserveArticlesUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideObserveArticleCategoriesUseCase(observeArticlesUseCase: ObserveArticlesUseCase)
            : ObserveArticleCategoriesUseCase =
        ObserveArticleCategoriesUseCase(observeArticlesUseCase)

}