package com.example.discover.domain.usecase

import com.example.discover.domain.model.Article
import com.example.discover.domain.model.Category
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GetArticleCategoriesUseCaseTest {

    private val useCase = GetArticleCategoriesUseCase()

    @Test
    fun `invoke with distinct categories returns all`() {
        val tech = Category("Tech")
        val sports = Category("Sports")

        val articles = listOf(
            Article(category = tech, title = "Tech 1", description = "desc", headerImageURL = "url"),
            Article(category = sports, title = "Sports 1", description = "desc", headerImageURL = "url")
        )

        val result = useCase(articles)

        assertThat(result).containsExactly(tech, sports)
    }

    @Test
    fun `invoke with duplicate categories returns distinct`() {
        val tech = Category("Tech")

        val articles = listOf(
            Article(category = tech, title = "Tech 1", description = "desc", headerImageURL = "url"),
            Article(category = tech, title = "Tech 2", description = "desc", headerImageURL = "url")
        )

        val result = useCase(articles)

        assertThat(result).containsExactly(tech)
    }

    @Test
    fun `invoke with empty list returns empty`() {
        val result = useCase(emptyList())

        assertThat(result).isEmpty()
    }
}
