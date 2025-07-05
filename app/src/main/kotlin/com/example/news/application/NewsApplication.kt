package com.example.news.application

import android.app.Application
import com.example.news.logging.PrefixedDebugTree
import com.example.news.utils.AppConstants.LOGGING_PREFIX
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        if (BuildConfig.DEBUG) {
        Timber.plant(PrefixedDebugTree(prefix = LOGGING_PREFIX))
//        }
    }
}
