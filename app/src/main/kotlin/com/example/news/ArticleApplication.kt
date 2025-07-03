package com.example.news

import android.app.Application

class ArticleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ArticleManager.initialize(this)
    }
}
