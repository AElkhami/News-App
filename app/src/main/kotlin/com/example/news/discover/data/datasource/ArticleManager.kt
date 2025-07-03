package com.example.news.discover.data.datasource

import android.content.Context
import com.example.news.discover.data.model.ArticleDto
import com.example.news.discover.data.model.ArticleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

object ArticleManager {

    private lateinit var context: Context

    fun initialize(context: Context) {
        ArticleManager.context = context
    }

    suspend fun getArticles(): List<ArticleDto> = withContext(Dispatchers.IO) {
        val jsonString = readJsonFromAssets(context)
        parseJson(jsonString)
    }

    private fun parseJson(jsonString: String): List<ArticleDto> {
        return Json.decodeFromString<ArticleResponse>(jsonString).articles
    }
}

fun readJsonFromAssets(context: Context): String {
    return context.assets.open("api_response.json").bufferedReader().use {
        it.readText()
    }
}
