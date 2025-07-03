package com.example.news.core.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.news.core.ui.theme.AppTheme
import com.example.news.core.ui.theme.LocalAppColors
import com.example.news.core.ui.theme.LocalAppDimens
import com.example.news.core.ui.theme.LocalAppTypography

@Composable
fun ScreenHeader(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    val dimens = LocalAppDimens.current
    val color = LocalAppColors.current
    val typography = LocalAppTypography.current

    Column(modifier = modifier
        .fillMaxWidth()
        .padding(dimens.mediumPadding)) {
        Text(text = title, style = typography.headlineLarge, color = color.textPrimary)
        Spacer(modifier = Modifier.height(dimens.smallPadding))
        Text(text = description, style = typography.bodyMedium, color = color.textSecondary)
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenHeaderPreview() {
    AppTheme {
        ScreenHeader(
            title = "Discover",
            description = "News from all around the world"
        )
    }
}