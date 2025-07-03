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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.news.core.ui.composables.ArticleItem
import com.example.news.core.ui.composables.CategoryTab
import com.example.news.core.ui.composables.ScreenHeader
import com.example.news.core.ui.theme.AppTheme
import com.example.news.core.ui.theme.LocalAppColors
import com.example.news.core.ui.theme.LocalAppDimens
import com.example.news.discover.domain.Article

@Composable
fun ArticleScreen() {
    ArticleScreenContent()
}

@Composable
fun ArticleScreenContent() {

    val mockArticles = listOf(
        Article(
            category = "Health",
            title = "Breaking Developments in Heart Health",
            description = "Latest advancements in cardiology and heart care...",
            headerImageURL = "https://example.com/images/heart-health.jpg"
        ),
        Article(
            category = "Technology",
            title = "Cybersecurity in the Modern Age",
            description = "Understanding the evolving landscape of cybersecurity threats...",
            headerImageURL = "https://example.com/images/cybersecurity.jpg"
        ),
        Article(
            category = "Entertainment",
            title = "The New Wave of Online Streaming",
            description = "Exploring the impact of streaming services on television and movies...Exploring the impact of streaming services on television and movies...Exploring the impact of streaming services on television and movies...Exploring the impact of streaming services on television and movies...Exploring the impact of streaming services on television and movies...Exploring the impact of streaming services on television and movies...",
            headerImageURL = "https://example.com/images/streaming.jpg"
        )
    )

    val mockCategories = listOf(
        "All", "Health", "Technology", "Entertainment", "Opinion"
    )

    val dimens = LocalAppDimens.current
    val color = LocalAppColors.current

    Column(modifier = Modifier.background(color = color.background).fillMaxSize()) {
        ScreenHeader(
            modifier = Modifier.padding(vertical = dimens.smallPadding),
            title = "Discover",
            description = "News from all around the world"
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = dimens.mediumPadding),
            horizontalArrangement = Arrangement.spacedBy(dimens.smallPadding)
        ) {
            items(mockCategories) { category ->
                CategoryTab(
                    tabName = category,
                    isSelected = false
                )
            }
        }

        Spacer(modifier = Modifier.height(dimens.smallPadding))

        LazyColumn(
            contentPadding = PaddingValues(dimens.mediumPadding),
            verticalArrangement = Arrangement.spacedBy(dimens.mediumPadding)
        ) {
            items(mockArticles) { article ->
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
fun ArticleScreenPreview() {
    AppTheme {
        ArticleScreenContent()
    }
}