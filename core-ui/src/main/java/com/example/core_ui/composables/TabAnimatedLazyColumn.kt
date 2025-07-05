package com.example.core_ui.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.core_ui.theme.LocalAppDimens

private const val DEFAULT_ANIMATION_DURATION_MS = 300
private const val DEFAULT_SCROLL_POSITION = 0
private const val DEFAULT_DIRECTION = +1
private const val REVERSE_DIRECTION = -1
private const val DEFAULT_TAB_INDEX = 0

@Composable
fun <T : Any> TabAnimatedLazyColumn(
    targetState: T,
    allStates: List<T>,
    items: List<Any>,
    listState: LazyListState,
    modifier: Modifier = Modifier,
    animationDuration: Int = DEFAULT_ANIMATION_DURATION_MS,
    key: (Any) -> Any = { it.hashCode() },
    itemContent: @Composable (Any) -> Unit
) {
    val dimens = LocalAppDimens.current

    var previousState by remember { mutableStateOf(targetState) }

    val previousIndex = allStates.indexOf(previousState).coerceAtLeast(DEFAULT_TAB_INDEX)
    val currentIndex = allStates.indexOf(targetState).coerceAtLeast(DEFAULT_TAB_INDEX)

    val direction = when {
        currentIndex > previousIndex -> DEFAULT_DIRECTION
        currentIndex < previousIndex -> REVERSE_DIRECTION
        else -> DEFAULT_DIRECTION
    }

    LaunchedEffect(targetState) {
        listState.scrollToItem(DEFAULT_SCROLL_POSITION)
    }

    AnimatedContent(
        targetState = targetState,
        modifier = modifier,
        transitionSpec = {
            if (direction > 0) {
                slideInHorizontally(
                    animationSpec = tween(animationDuration),
                    initialOffsetX = { it }
                ) + fadeIn(animationSpec = tween(animationDuration)) togetherWith
                        slideOutHorizontally(
                            animationSpec = tween(animationDuration),
                            targetOffsetX = { -it }
                        ) + fadeOut(animationSpec = tween(animationDuration))
            } else {
                slideInHorizontally(
                    animationSpec = tween(animationDuration),
                    initialOffsetX = { -it }
                ) + fadeIn(animationSpec = tween(animationDuration)) togetherWith
                        slideOutHorizontally(
                            animationSpec = tween(animationDuration),
                            targetOffsetX = { it }
                        ) + fadeOut(animationSpec = tween(animationDuration))
            }
        }
    ) { state ->
        LaunchedEffect(state) {
            previousState = state
        }

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(dimens.mediumPadding),
            verticalArrangement = Arrangement.spacedBy(dimens.smallPadding)
        ) {
            items(
                items = items,
                key = { key(it) }
            ) { item ->
                itemContent(item)
            }
        }
    }
}
