package com.example.discover.domain.usecase

import com.example.core.error.AppError
import com.example.core.util.Result
import com.example.discover.domain.model.Article
import com.example.discover.domain.model.Category
import com.example.discover.domain.repository.ArticleRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetArticlesUseCaseTest {

    private val repository: ArticleRepository = mockk()
    private lateinit var useCase: GetArticlesUseCase

    @Before
    fun setUp() {
        useCase = GetArticlesUseCase(repository)
    }

    @Test
    fun `invoke returns success when repository returns success`() = runTest {
        val articles = listOf(
            Article(
                category = Category("Tech"),
                title = "Tech 1",
                description = "desc",
                headerImageURL = "url"
            )
        )

        coEvery { repository.getArticles() } returns Result.Success(articles)

        val result = useCase()

        assertThat(result).isEqualTo(Result.Success(articles))
    }

    @Test
    fun `invoke returns error when repository returns error`() = runTest {
        val error = AppError.ParseError

        coEvery { repository.getArticles() } returns Result.Error(error)

        val result = useCase()

        assertThat(result).isEqualTo(Result.Error(error))
    }

}
