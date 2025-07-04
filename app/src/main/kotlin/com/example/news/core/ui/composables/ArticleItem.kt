package com.example.news.core.ui.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.example.news.R
import com.example.news.core.ui.theme.AppTheme
import com.example.news.core.ui.theme.LocalAppColors
import com.example.news.core.ui.theme.LocalAppDimens
import com.example.news.core.ui.theme.LocalAppTypography
import com.example.news.discover.domain.model.Category

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    description: String,
    category: Category
) {
    val dimens = LocalAppDimens.current
    val color = LocalAppColors.current
    val typography = LocalAppTypography.current

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(dimens.articleImageSize),
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.article_placeholder),
            error = painterResource(R.drawable.article_error)
        )
        Column(
            modifier = Modifier
                .padding(
                    start = dimens.mediumPadding,
                    top = dimens.smallPadding,
                    bottom = dimens.smallPadding
                )
        ) {
            Text(
                text = category.name,
                style = typography.bodySmall,
                color = color.textSecondary
            )
            Spacer(modifier = Modifier.height(dimens.smallPadding))
            Text(
                text = title,
                style = typography.titleMedium,
                color = color.textPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(dimens.smallPadding))
            Text(
                text = description,
                style = typography.bodySmall,
                color = color.textSecondary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun ArticleItemPreview() {
    AppTheme {
        ArticleItem(
            imageUrl = "https://example.com/images/heart-health.jpg",
            title = "Breaking Developments in Heart Health",
            description = "Latest advancements in cardiology and heart care...",
            category = Category("Health")
        )
    }
}