package com.example.core_ui.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.imageLoader
import com.example.core_ui.theme.AppTheme
import com.example.core_ui.theme.LocalAppColors
import com.example.core_ui.theme.LocalAppDimens
import com.example.core_ui.theme.LocalAppTypography

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
fun ScreenHeaderPreview() {
    AppTheme(
        imageLoader = LocalContext.current.imageLoader
    ) {
        ScreenHeader(
            title = "Discover",
            description = "News from all around the world"
        )
    }
}