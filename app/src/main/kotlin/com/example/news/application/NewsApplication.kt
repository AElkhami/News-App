package com.example.news.application

import android.app.Application
import com.example.news.discover.data.datasource.ArticleManager

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ArticleManager.initialize(this)
    }
}
