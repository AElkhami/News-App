package com.example.discover.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil3.imageLoader
import com.example.core_ui.state.ScreenState
import com.example.core_ui.theme.AppTheme
import com.example.discover.domain.model.Article
import com.example.discover.domain.model.Category
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiscoverScreenContentTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    private val articles = listOf(
        Article(
            category = Category("Health"),
            title = "Heart Health",
            description = "Latest in cardiology",
            headerImageURL = ""
        ),
        Article(
            category = Category("Technology"),
            title = "Cybersecurity",
            description = "Evolving threats",
            headerImageURL = ""
        )
    )

    private val categories = listOf(
        Category("All"),
        Category("Health"),
        Category("Technology")
    )

    @Test
    fun contentState_displaysArticlesAndCategories() {
        composeRule.setContent {
            AppTheme(
                imageLoader = LocalContext.current.imageLoader
            ) {
                DiscoverScreenContent(
                    articles = articles,
                    categories = categories,
                    selectedCategory = "All",
                    screenState = ScreenState.Content,
                    onTabClick = {},
                    onRetryClick = {}
                )
            }
        }

        // Screen header
        composeRule.onNodeWithText("Discover").assertIsDisplayed()
        composeRule.onNodeWithText("News from all around the world").assertIsDisplayed()

        // Categories
        categories.forEach {
            composeRule.onNodeWithTag("CategoryTab_${it.name}").assertIsDisplayed()
        }

        // Articles
        articles.forEach {
            composeRule.onNodeWithTag("ArticleItem_${it.title}").assertIsDisplayed()
            composeRule.onNodeWithText(it.description).assertIsDisplayed()
        }
    }

    @Test
    fun loadingState_displaysLoadingIndicator() {
        composeRule.setContent {
            AppTheme(
                imageLoader = LocalContext.current.imageLoader
            ) {
                DiscoverScreenContent(
                    articles = emptyList(),
                    categories = emptyList(),
                    selectedCategory = "All",
                    screenState = ScreenState.Loading,
                    onTabClick = {},
                    onRetryClick = {}
                )
            }
        }

        composeRule.onNodeWithTag("LoadingIndicator").assertIsDisplayed()
    }

    @Test
    fun errorState_displaysErrorContent() {
        composeRule.setContent {
            AppTheme(
                imageLoader = LocalContext.current.imageLoader
            ) {
                DiscoverScreenContent(
                    articles = emptyList(),
                    categories = emptyList(),
                    selectedCategory = "All",
                    screenState = ScreenState.Error(
                        message = com.example.core_ui.UiText.DynamicString("Something went wrong.")
                    ),
                    onTabClick = {},
                    onRetryClick = {}
                )
            }
        }

        composeRule.onNodeWithText("Something went wrong.").assertIsDisplayed()
    }

    @Test
    fun tabClick_invokesCallback() {
        var clickedCategory: String? = null

        composeRule.setContent {
            AppTheme(
                imageLoader = LocalContext.current.imageLoader
            ) {
                DiscoverScreenContent(
                    articles = articles,
                    categories = categories,
                    selectedCategory = "All",
                    screenState = ScreenState.Content,
                    onTabClick = { clickedCategory = it },
                    onRetryClick = {}
                )
            }
        }

        composeRule.onNodeWithTag("CategoryTab_Technology").performClick()

        composeRule.runOnIdle {
            assert(clickedCategory == "Technology") {
                "Expected clickedCategory to be Technology but was $clickedCategory"
            }
        }
    }
}
