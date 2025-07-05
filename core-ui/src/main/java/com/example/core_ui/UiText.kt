package com.example.core_ui

import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.parcelize.Parcelize


/**
 * Represents a string that can either be a plain dynamic [String] or a string resource.
 *
 * This is useful for unifying text representations in view models and UI layers
 * without leaking Android framework APIs into domain or data layers.
 */
@Parcelize
sealed class UiText : Parcelable {
    data class DynamicString(val value: String) : UiText()
    class StringResource(
        @param:StringRes val resId: Int,
        vararg val args: String
    ) : UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(resId, *args)
        }
    }
}
