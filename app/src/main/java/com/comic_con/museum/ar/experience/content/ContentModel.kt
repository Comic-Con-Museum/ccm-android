package com.comic_con.museum.ar.experience.content

class ContentModel(
    val contentItems: List<ContentItem>
)

class ContentItem(
    val contentTitle: String,
    val contentId: String,
    val contentDescription: String,
    val contentImageURL: String,
    val contentCategories: List<String>,
    val contentPairs: List<ContentPair>
)

class ContentPair(
    val label: String,
    val value: String
)