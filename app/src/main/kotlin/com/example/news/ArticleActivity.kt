package com.example.news

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.ArticleManager.getArticles
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticleActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private lateinit var recyclerView: RecyclerView
    private var adapter: ArticleAdapter = ArticleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        recyclerView = findViewById(R.id.recyclerViewArticles)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadArticles()
    }

    private fun loadArticles() {
        coroutineScope.launch {
            try {
                val articles = withContext(Dispatchers.IO) {
                    getArticles()
                }
                adapter.updateArticles(articles)
            } catch (e: Exception) {
                Toast.makeText(this@ArticleActivity, "Error loading articles", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel() // Cancel coroutines on activity destroy
    }
}
