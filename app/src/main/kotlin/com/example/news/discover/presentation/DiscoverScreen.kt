package com.example.news.discover.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.news.core.ui.composables.ArticleItem
import com.example.news.core.ui.composables.CategoryTab
import com.example.news.core.ui.composables.ScreenHeader
import com.example.news.core.ui.theme.AppTheme
import com.example.news.core.ui.theme.LocalAppColors
import com.example.news.core.ui.theme.LocalAppDimens
import com.example.news.discover.domain.model.Article
import com.example.news.discover.domain.model.Category

@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadArticles()
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold { screenPadding ->
        DiscoverScreenContent(
            modifier = Modifier.padding(screenPadding),
            articles = uiState.filteredArticles,
            categories = uiState.categories,
            selectedCategory = uiState.selectedCategory,
            onTabClick = { category ->
                viewModel.showArticlesForCategory(category)
            }
        )
    }
}

@Composable
fun DiscoverScreenContent(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    categories: List<Category>,
    selectedCategory: String,
    onTabClick: (String) -> Unit
) {
    val dimens = LocalAppDimens.current
    val color = LocalAppColors.current

    Column(
        modifier = modifier
            .background(color = color.background)
            .fillMaxSize()
    ) {
        ScreenHeader(
            modifier = Modifier.padding(vertical = dimens.smallPadding),
            title = "Discover",
            description = "News from all around the world"
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = dimens.mediumPadding),
            horizontalArrangement = Arrangement.spacedBy(dimens.smallPadding)
        ) {
            items(categories) { category ->
                CategoryTab(
                    tabName = category.name,
                    isSelected = category.name == selectedCategory,
                    onClick = onTabClick
                )
            }
        }

        Spacer(modifier = Modifier.height(dimens.smallPadding))

        LazyColumn(
            contentPadding = PaddingValues(dimens.mediumPadding),
            verticalArrangement = Arrangement.spacedBy(dimens.mediumPadding)
        ) {
            items(articles) { article ->
                ArticleItem(
                    imageUrl = article.headerImageURL,
                    category = article.category,
                    title = article.title,
                    description = article.description
                )
            }
        }
    }

}

@Preview
@Composable
fun DiscoverScreenPreview() {
    val articles = listOf(
        Article(
            category = Category("Health"),
            title = "Breaking Developments in Heart Health",
            description = "Latest advancements in cardiology and heart care...",
            headerImageURL = "https://example.com/images/heart-health.jpg"
        ),
        Article(
            category = Category("Technology"),
            title = "Cybersecurity in the Modern Age",
            description = "Understanding the evolving landscape of cybersecurity threats...",
            headerImageURL = "https://example.com/images/cybersecurity.jpg"
        ),
        Article(
            category = Category("Entertainment"),
            title = "The New Wave of Online Streaming",
            description = "Exploring the impact of streaming services on television and movies...Exploring the impact of streaming services on television and movies...Exploring the impact of streaming services on television and movies...Exploring the impact of streaming services on television and movies...Exploring the impact of streaming services on television and movies...Exploring the impact of streaming services on television and movies...",
            headerImageURL = "https://example.com/images/streaming.jpg"
        )
    )

    val categories = listOf(
        Category("All"),
        Category("Health"),
        Category("Technology"),
        Category("Entertainment"),
        Category("Opinion")
    )
    AppTheme {
        DiscoverScreenContent(
            articles = articles,
            categories = categories,
            selectedCategory = Category.ALL.name,
            onTabClick = {})
    }
}