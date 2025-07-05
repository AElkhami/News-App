package com.example.news.application

import android.app.Application
import com.example.news.logging.PrefixedDebugTree
import com.example.news.utils.AppConstants.LOGGING_PREFIX
import com.example.news.BuildConfig.DEBUG
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (DEBUG) {
            Timber.plant(PrefixedDebugTree(prefix = LOGGING_PREFIX))
        }
    }
}
