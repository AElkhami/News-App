package com.example.core_ui.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import coil3.imageLoader
import com.example.core_ui.theme.AppTheme
import com.example.core_ui.theme.LocalAppColors
import com.example.core_ui.theme.LocalAppDimens
import com.example.core_ui.theme.LocalAppTypography

@Composable
fun CategoryTab(
    modifier: Modifier = Modifier,
    tabName: String,
    isSelected: Boolean,
    onClick: (String) -> Unit
) {
    val dimens = LocalAppDimens.current
    val color = LocalAppColors.current
    val typography = LocalAppTypography.current

    Row(
        modifier = modifier
            .testTag("CategoryTab_$tabName")
            .background(
                color = if (isSelected) color.primary else color.surface,
                shape = RoundedCornerShape(dimens.cardCornerRadius),
            )
            .padding(vertical = dimens.smallPadding, horizontal = dimens.mediumPadding)
            .clickable {
                onClick(tabName)
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = tabName,
            style = typography.bodySmall,
            color = if (isSelected) color.background else color.textSecondary
        )
    }
}

@Preview(
    name = "Light Mode",
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun CategoryTabPreview() {
    AppTheme(
        imageLoader = LocalContext.current.imageLoader
    ) {
        CategoryTab(tabName = "All", isSelected = false, onClick = {})
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
fun CategoryTabSelectedPreview() {
    AppTheme(
        imageLoader = LocalContext.current.imageLoader
    ) {
        CategoryTab(tabName = "All", isSelected = true, onClick = {})
    }
}