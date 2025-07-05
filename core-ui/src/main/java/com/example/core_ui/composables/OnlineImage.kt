package com.example.core_ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.imageLoader
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.core_ui.R
import com.example.core_ui.theme.LocalAppDimens

@Composable
fun OnlineImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader = LocalContext.current.imageLoader
) {
    val dimens = LocalAppDimens.current

    AsyncImage(
        modifier = modifier
            .testTag("OnlineImage")
            .size(dimens.articleImageSize),
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        imageLoader = imageLoader,
        contentDescription = stringResource(R.string.article_image),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.article_placeholder),
        error = painterResource(R.drawable.article_error)
    )
}