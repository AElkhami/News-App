package com.example.news.discover.data.mapper

import com.example.news.discover.data.model.ArticleDto
import com.example.news.discover.domain.model.Article
import com.example.news.discover.domain.model.Category

fun ArticleDto.toDomain() = Article(
    category = Category(name = type),
    title = title,
    description = description,
    headerImageURL = headerImageURL
)