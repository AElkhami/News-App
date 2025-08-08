package com.example.discover.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.imageLoader
import com.example.core_ui.UiText
import com.example.core_ui.composables.ArticleItem
import com.example.core_ui.composables.CategoryTab
import com.example.core_ui.composables.ErrorContent
import com.example.core_ui.composables.LoadingIndicator
import com.example.core_ui.composables.ScreenHeader
import com.example.core_ui.composables.TabAnimatedLazyColumn
import com.example.core_ui.state.ScreenState
import com.example.core_ui.theme.AppDimens
import com.example.core_ui.theme.AppTheme
import com.example.core_ui.theme.LocalAppColors
import com.example.core_ui.theme.LocalAppDimens
import com.example.discover.domain.model.Article
import com.example.discover.domain.model.Category

@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadArticles()
    }

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
    ) { screenPadding ->
        DiscoverScreenContent(
            modifier = Modifier.padding(screenPadding),
            articles = uiState.filteredArticles,
            categories = uiState.categories,
            selectedCategory = uiState.selectedCategory,
            screenState = uiState.screenState,
            onTabClick = { category ->
                viewModel.showArticlesForCategory(category)
            },
            onRetryClick = {
                viewModel.loadArticles()
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
    screenState: ScreenState<List<Article>>,
    onTabClick: (String) -> Unit,
    onRetryClick: () -> Unit
) {
    val dimens = LocalAppDimens.current
    val color = LocalAppColors.current

    val listState = rememberLazyListState()
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

        when (screenState) {
            is ScreenState.Content -> {
                CategoriesList(
                    dimens = dimens,
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onTabClick = onTabClick
                )
                Spacer(modifier = Modifier.height(dimens.smallPadding))
                ArticlesList(
                    selectedCategory = selectedCategory,
                    articles = articles,
                    categories = categories,
                    listState = listState
                )
            }

            is ScreenState.Error -> ErrorContent(
                message = screenState.message.asString(),
                onRetry = { onRetryClick() }
            )

            ScreenState.Loading -> LoadingIndicator()
        }
    }
}

@Composable
private fun CategoriesList(
    dimens: AppDimens,
    categories: List<Category>,
    selectedCategory: String,
    onTabClick: (String) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = dimens.mediumPadding),
        horizontalArrangement = Arrangement.spacedBy(dimens.smallPadding)
    ) {
        items(
            items = categories,
            key = { category -> category.name }
        ) { category ->
            CategoryTab(
                tabName = category.name,
                isSelected = category.name == selectedCategory,
                onClick = { onTabClick(it) }
            )
        }
    }
}

@Composable
private fun ArticlesList(
    articles: List<Article>,
    categories: List<Category>,
    selectedCategory: String,
    listState: LazyListState
) {
    TabAnimatedLazyColumn(
        targetState = selectedCategory,
        allStates = categories.map { it.name },
        items = articles,
        listState = listState,
        key = { article ->
            "${(article as Article).title}_${article.category.name}"
        }
    ) { item ->
        val article = item as Article
        ArticleItem(
            imageUrl = article.headerImageURL,
            category = article.category.name,
            title = article.title,
            description = article.description
        )
    }
}

@Preview(
    name = "Light Mode",
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    uiMode = UI_MODE_NIGHT_YES
)
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
    AppTheme(
        imageLoader = LocalContext.current.imageLoader
    ) {
        DiscoverScreenContent(
            articles = articles,
            categories = categories,
            selectedCategory = Category.ALL.name,
            screenState = ScreenState.Content(articles),
            onTabClick = {},
            onRetryClick = {}
        )
    }
}

@Preview(
    name = "Light Mode",
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun DiscoverScreenLoadingPreview() {
    AppTheme(
        imageLoader = LocalContext.current.imageLoader
    ) {
        DiscoverScreenContent(
            articles = emptyList(),
            categories = emptyList(),
            selectedCategory = Category.ALL.name,
            screenState = ScreenState.Loading,
            onTabClick = {},
            onRetryClick = {}
        )
    }
}

@Preview(
    name = "Light Mode",
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun DiscoverScreenErrorPreview() {
    AppTheme(
        imageLoader = LocalContext.current.imageLoader
    ) {
        DiscoverScreenContent(
            articles = emptyList(),
            categories = emptyList(),
            selectedCategory = Category.ALL.name,
            screenState = ScreenState.Error(
                message = UiText.DynamicString("Something went wrong.")
            ),
            onTabClick = {},
            onRetryClick = {}
        )
    }
}