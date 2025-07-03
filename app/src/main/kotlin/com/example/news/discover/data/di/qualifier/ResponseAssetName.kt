package com.example.news.discover.data.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Target(
    allowedTargets = [
        AnnotationTarget.FUNCTION,
        AnnotationTarget.VALUE_PARAMETER
    ]
)
@Retention(AnnotationRetention.BINARY)
annotation class ResponseAssetName