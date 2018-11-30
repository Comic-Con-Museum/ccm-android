package com.comic_con.museum.ar.overview

import com.comic_con.museum.ar.experience.progress.ProgressModel

class ExhibitModel(
    val title: String,
    val id: String,
    val description: String,
    val additionalDescription: String,
    val imageUrl: String,
    val contentTags: List<String>,
    val category: CategoryModel,
    val content: ContentModel,
    val progress: ProgressModel
)

class CategoryModel(
    val categories: List<ContentItem>
)

class ContentModel(
    val contentItems: List<ContentItem>
)

class ContentItem(
    val title: String,
    val id: String,
    val description: String,
    val imageUrl: String,
    val tags: List<String>,
    val categories: List<String>?,
    val extraPairs: List<ContentPair>?
)

class ContentPair(
    val label: String,
    val value: String,
    val contentReferenceId: String
)