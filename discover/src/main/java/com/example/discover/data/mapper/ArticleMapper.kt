package com.example.discover.data.mapper

import com.example.discover.data.model.ArticleDto
import com.example.discover.domain.model.Article
import com.example.discover.domain.model.Category

fun ArticleDto.toDomain() = Article(
    category = Category(name = type),
    title = title,
    description = description,
    headerImageURL = headerImageURL
)