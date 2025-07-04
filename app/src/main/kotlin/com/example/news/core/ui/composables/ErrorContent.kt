package com.example.news.core.ui.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.news.R
import com.example.news.core.ui.theme.AppTheme
import com.example.news.core.ui.theme.LocalAppColors
import com.example.news.core.ui.theme.LocalAppDimens
import com.example.news.core.ui.theme.LocalAppTypography

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    message: String,
    onRetry: () -> Unit
) {
    val dimens = LocalAppDimens.current
    val typography = LocalAppTypography.current
    val colors = LocalAppColors.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = message,
                style = typography.bodyMedium,
                color = colors.error,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(dimens.mediumPadding))

            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(containerColor = colors.primary)
            ) {
                Text(
                    text = stringResource(R.string.button_retry),
                    style = typography.labelLarge,
                    color = colors.surface
                )
            }
        }
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
fun ErrorContentPreview() {
    AppTheme {
        ErrorContent(message = "Something went wrong!", onRetry = {})
    }
}