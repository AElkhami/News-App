package com.example.news

import android.content.Context
import android.content.res.AssetManager
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import java.io.ByteArrayInputStream
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

class ArticleManagerTest {

    private lateinit var mockContext: Context
    private lateinit var mockAssetManager: AssetManager

    @Before
    fun setUp() {
        mockContext = mock(Context::class.java)
        mockAssetManager = mock(AssetManager::class.java)
        `when`(mockContext.assets).thenReturn(mockAssetManager)

        val json = """
            { "articles" : [
                {"type": "News", "title": "Mock Title 1", "description": "Mock Description 1", "headerImageURL": "http://example.com/image1.jpg"},
                {"type": "Opinion", "title": "Mock Title 2", "description": "Mock Description 2", "headerImageURL": "http://example.com/image2.jpg"}
            ] }
            """

        val inputStream = ByteArrayInputStream(json.toByteArray(Charsets.UTF_8))
        `when`(mockAssetManager.open(anyString())).thenReturn(inputStream)
        ArticleManager.initialize(mockContext)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getArticles returns a list of articles`() = runTest {
        val articles = ArticleManager.getArticles()

        assertNotNull(articles)
        assertEquals(2, articles.size)
        assertEquals("Mock Title 1", articles[0].title)
        assertEquals("Opinion", articles[1].type)
    }
}
