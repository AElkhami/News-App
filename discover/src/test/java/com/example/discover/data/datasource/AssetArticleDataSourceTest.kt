package com.example.discover.data.datasource

import com.example.core.error.AppError
import com.example.core.util.Result
import com.example.core.util.JsonParser
import com.example.discover.data.model.ArticleResponse
import com.example.discover.data.utils.AssetReader
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.SerializationException
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AssetArticleDataSourceTest {

    private val assetReader: AssetReader = mockk()
    private val jsonParser: JsonParser = mockk()
    private val dispatcher = UnconfinedTestDispatcher()

    private val assetName = "test.json"

    private lateinit var dataSource: AssetArticleDataSource

    @Before
    fun setUp() {
        dataSource = AssetArticleDataSource(
            assetReader,
            jsonParser,
            dispatcher,
            assetName
        )
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

        val response = ArticleResponse(
            articles = listOf(
                com.example.discover.data.model.ArticleDto(
                    type = "Tech",
                    title = "Title",
                    description = "Desc",
                    headerImageURL = "url"
                )
            )
        )

        coEvery { assetReader.read(assetName) } returns jsonString
        coEvery { jsonParser.parse(jsonString, ArticleResponse.serializer()) } returns response

        val result = dataSource.fetchArticles()

        assertThat(result).isInstanceOf(Result.Success::class.java)
        val articles = (result as Result.Success).data
        assertThat(articles).hasSize(1)
        assertThat(articles.first().title).isEqualTo("Title")
    }

    @Test
    fun `fetchArticles returns FileNotFound when asset not found`() = runTest {
        coEvery { assetReader.read(assetName) } throws java.io.FileNotFoundException()

        val result = dataSource.fetchArticles()

        assertThat(result).isEqualTo(Result.Error(AppError.FileNotFound))
    }

    @Test
    fun `fetchArticles returns ParseError when JSON invalid`() = runTest {
        val invalidJson = "INVALID_JSON"

        coEvery { assetReader.read(assetName) } returns invalidJson
        coEvery { jsonParser.parse(invalidJson, ArticleResponse.serializer()) } throws SerializationException("Invalid JSON")

        val result = dataSource.fetchArticles()

        assertThat(result).isEqualTo(Result.Error(AppError.ParseError))
    }

    @Test
    fun `fetchArticles returns InvalidData when IllegalArgumentException thrown`() = runTest {
        coEvery { assetReader.read(assetName) } throws IllegalArgumentException("bad file")

        val result = dataSource.fetchArticles()

        assertThat(result).isEqualTo(Result.Error(AppError.InvalidData))
    }

    @Test
    fun `fetchArticles returns Unknown on other exceptions`() = runTest {
        coEvery { assetReader.read(assetName) } throws RuntimeException("something went wrong")

        val result = dataSource.fetchArticles()

        assertThat(result).isEqualTo(Result.Error(AppError.Unknown))
    }
}
