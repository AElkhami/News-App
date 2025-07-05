package com.example.core.util

import javax.inject.Inject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

class JsonParser @Inject constructor(
    private val json: Json
) {
    fun <T> parse(raw: String, serializer: KSerializer<T>): T {
        return json.decodeFromString(serializer, raw)
    }
}