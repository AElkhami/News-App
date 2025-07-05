package com.example.discover.data.repository

import com.example.core.error.AppError
import com.example.core.util.Result
import com.example.discover.domain.datasource.ArticleDataSource
import com.example.discover.domain.model.Article
import com.example.discover.domain.model.Category
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AssetsArticleRepositoryTest {

    private val dataSource: ArticleDataSource = mockk()
    private lateinit var repository: AssetsArticleRepository

    @Before
    fun setUp() {
        repository = AssetsArticleRepository(dataSource)
    }

    @Test
    fun `getArticles returns success when dataSource returns success`() = runTest {
        val articles = listOf(
            Article(
                category = Category("Tech"),
                title = "Title",
                description = "Desc",
                headerImageURL = "url"
            )
        )

        coEvery { dataSource.fetchArticles() } returns Result.Success(articles)

        val result = repository.getArticles()

        assertThat(result).isInstanceOf(Result.Success::class.java)
        val returnedArticles = (result as Result.Success).data
        assertThat(returnedArticles).isEqualTo(articles)
    }

    @Test
    fun `getArticles returns error when dataSource returns error`() = runTest {
        val error = Result.Error(AppError.ParseError)

        coEvery { dataSource.fetchArticles() } returns error

        val result = repository.getArticles()

        assertThat(result).isEqualTo(error)
    }
}
