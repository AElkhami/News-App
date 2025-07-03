package com.example.news.core.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.news.core.ui.theme.AppTheme
import com.example.news.core.ui.theme.LocalAppColors
import com.example.news.core.ui.theme.LocalAppDimens
import com.example.news.core.ui.theme.LocalAppTypography

@Composable
fun CategoryTab(
    modifier: Modifier = Modifier,
    tabName: String,
    isSelected: Boolean) {
    val dimens = LocalAppDimens.current
    val color = LocalAppColors.current
    val typography = LocalAppTypography.current

    Row(
        modifier = modifier
            .background(
                color = if(isSelected) color.primary else color.surface,
                shape = RoundedCornerShape(dimens.cardCornerRadius),
            )
            .padding(vertical = dimens.smallPadding, horizontal = dimens.mediumPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = tabName,
            style = typography.bodySmall,
            color = if(isSelected) color.background else color.textSecondary
        )
    }
}

@Preview
@Composable
fun CategoryTabPreview() {
    AppTheme {
        CategoryTab(tabName = "All", isSelected = false)
    }
}

@Preview
@Composable
fun CategoryTabSelectedPreview() {
    AppTheme {
        CategoryTab(tabName = "All", isSelected = true)
    }
}