package com.example.news.discover.data.mapper

import com.example.news.discover.data.model.ArticleDto
import com.example.news.discover.domain.Article

fun ArticleDto.toDomain() = Article(
    category = type,
    title = title,
    description = description,
    headerImageURL = headerImageURL
)