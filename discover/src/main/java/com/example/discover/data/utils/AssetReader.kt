package com.example.discover.data.utils

import android.content.res.AssetManager
import javax.inject.Inject

/**
 * Utility class for reading Android assets as strings.
 */
class AssetReader @Inject constructor(
    private val assets: AssetManager
) {
    fun read(assetName: String): String {
        return assets.open(assetName).bufferedReader().use { it.readText() }
    }
}