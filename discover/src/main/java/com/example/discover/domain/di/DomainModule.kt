package com.example.discover.domain.di

import com.example.discover.domain.repository.ArticleRepository
import com.example.discover.domain.usecase.GetArticleCategoriesUseCase
import com.example.discover.domain.usecase.GetArticlesUseCase
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
    fun provideGetArticlesUseCase(repository: ArticleRepository)
            : GetArticlesUseCase = GetArticlesUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetArticleCategoriesUseCase()
            : GetArticleCategoriesUseCase =
        GetArticleCategoriesUseCase()
}