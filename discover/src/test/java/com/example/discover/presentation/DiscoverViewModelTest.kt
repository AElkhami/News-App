package com.example.discover.presentation

import dispatcher.CoroutineDispatcherRule
import app.cash.turbine.test
import com.example.core.error.AppError
import com.example.core_ui.state.ScreenState
import com.example.discover.domain.model.Article
import com.example.discover.domain.model.Category
import com.example.discover.domain.usecase.GetArticleCategoriesUseCase
import com.example.discover.domain.usecase.GetArticlesUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.example.core.util.Result

@OptIn(ExperimentalCoroutinesApi::class)
class DiscoverViewModelTest {

    @get:Rule
    val dispatcherRule = CoroutineDispatcherRule(StandardTestDispatcher())

    private val getArticles: GetArticlesUseCase = mockk()
    private val getCategories: GetArticleCategoriesUseCase = mockk()

    private lateinit var viewModel: DiscoverViewModel

    @Before
    fun setUp() {
        viewModel = DiscoverViewModel(getArticles, getCategories)
    }

    @Test
    fun loadArticles_emitsContentState_withArticlesAndCategories_onSuccess() = runTest {
        // Arrange
        val articles = listOf(
            Article(
                title = "Tech 1", description = "Desc",
                category = Category("Tech"), headerImageURL = "image url"
            )
        )
        val categories = listOf(Category("Tech"))

        coEvery { getArticles() } returns Result.Success(articles)
        coEvery { getCategories(articles) } returns categories

        // Act
        viewModel.loadArticles()

        // Assert
        viewModel.uiState.test {
            val loading = awaitItem() // first item is already Loading
            assertThat(loading.screenState).isInstanceOf(ScreenState.Loading::class.java)

            val content = awaitItem()
            assertThat(content.screenState).isEqualTo(ScreenState.Content)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun showArticlesForCategory_withAll_returnsAllArticles() {
        // Arrange
        val articles = listOf(
            Article(
                title = "Tech 1", description = "Desc",
                category = Category("Tech"), headerImageURL = "image url"
            ),
            Article(
                title = "Sports 1", description = "Desc",
                category = Category("Sports"), headerImageURL = "image url"
            )
        )

        val initialState = DiscoverUiState(
            screenState = ScreenState.Content,
            articles = articles,
            filteredArticles = emptyList(),
            categories = listOf(Category.ALL, Category("Tech"), Category("Sports"))
        )

        viewModel = DiscoverViewModel(getArticles, getCategories).apply {
            val field = this::class.java.getDeclaredField("_uiState")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            (field.get(this) as MutableStateFlow<DiscoverUiState>).value = initialState
        }

        // Act
        viewModel.showArticlesForCategory(Category.ALL.name)

        // Assert
        val state = viewModel.uiState.value
        assertThat(state.selectedCategory).isEqualTo(Category.ALL.name)
        assertThat(state.filteredArticles).containsExactlyElementsIn(articles)
    }


    @Test
    fun loadArticles_emitsErrorState_onFailure() = runTest {
        val error = AppError.ParseError
        coEvery { getArticles() } returns Result.Error(error)

        viewModel.loadArticles()

        viewModel.uiState.test {
            val loading = awaitItem()
            assertThat(loading.screenState).isInstanceOf(ScreenState.Loading::class.java)

            val errorState = awaitItem()
            assertThat(errorState.screenState).isInstanceOf(ScreenState.Error::class.java)

            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun showArticlesForCategory_filtersCorrectly() {
        // Arrange
        val articles = listOf(
            Article(
                title = "Tech 1", description = "Desc",
                category = Category("Tech"), headerImageURL = "image url"
            ),
            Article(
                title = "Tech 2", description = "Desc2",
                category = Category("Tech2"), headerImageURL = "image url2"
            )
        )

        val initialState = DiscoverUiState(
            screenState = ScreenState.Content,
            articles = articles,
            filteredArticles = articles,
            categories = listOf(Category.ALL, Category("Tech"), Category("Sports"))
        )

        viewModel = DiscoverViewModel(getArticles, getCategories).apply {
            val field = this::class.java.getDeclaredField("_uiState")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            (field.get(this) as MutableStateFlow<DiscoverUiState>).value = initialState
        }

        // Act
        viewModel.showArticlesForCategory("Tech")

        // Assert
        val state = viewModel.uiState.value
        assertThat(state.selectedCategory).isEqualTo("Tech")
        assertThat(state.filteredArticles.all { it.category.name == "Tech" }).isTrue()
    }
}
