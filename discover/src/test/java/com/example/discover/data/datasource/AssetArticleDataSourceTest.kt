package com.example.discover.data.datasource

import android.content.res.AssetManager
import com.example.core.error.AppError
import com.example.core.util.Result
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import java.io.FileNotFoundException
import java.io.InputStream
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AssetArticleDataSourceTest {

    private val assetManager: AssetManager = mockk()
    private val json: Json = Json { ignoreUnknownKeys = true }
    private val dispatcher = UnconfinedTestDispatcher()

    private val assetName = "test.json"

    private lateinit var dataSource: AssetArticleDataSource

    @Before
    fun setUp() {
        dataSource = AssetArticleDataSource(assetManager, json, dispatcher, assetName)
    }

    @Test
    fun `fetchArticles returns Success when JSON is valid`() = runTest {
        val jsonString = """
                              {
                                  "articles": [
                                      {
                                          "type": "Tech",
                                          "title": "Title",
                                          "description": "Desc",
                                          "headerImageURL": "url"
                                      }
                                  ]
                              }
                          """.trimIndent()


        val stream: InputStream = jsonString.byteInputStream()
        every { assetManager.open(assetName) } returns stream

        val result = dataSource.fetchArticles()

        assertThat(result).isInstanceOf(Result.Success::class.java)
        val articles = (result as Result.Success).data
        assertThat(articles).isNotEmpty()
    }

    @Test
    fun `fetchArticles returns FileNotFound when asset not found`() = runTest {
        every { assetManager.open(assetName) } throws FileNotFoundException()

        val result = dataSource.fetchArticles()

        assertThat(result).isEqualTo(Result.Error(AppError.FileNotFound))
    }

    @Test
    fun `fetchArticles returns ParseError when JSON invalid`() = runTest {
        val invalidJson = "INVALID_JSON"
        val stream: InputStream = invalidJson.byteInputStream()
        every { assetManager.open(assetName) } returns stream

        val result = dataSource.fetchArticles()

        assertThat(result).isEqualTo(Result.Error(AppError.ParseError))
    }

    @Test
    fun `fetchArticles returns InvalidData when IllegalArgumentException thrown`() = runTest {
        every { assetManager.open(assetName) } throws IllegalArgumentException("bad file")

        val result = dataSource.fetchArticles()

        assertThat(result).isEqualTo(Result.Error(AppError.InvalidData))
    }

    @Test
    fun `fetchArticles returns Unknown on other exceptions`() = runTest {
        every { assetManager.open(assetName) } throws RuntimeException("something went wrong")

        val result = dataSource.fetchArticles()

        assertThat(result).isEqualTo(Result.Error(AppError.Unknown))
    }
}
