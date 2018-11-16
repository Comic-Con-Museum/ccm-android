package com.comic_con.museum.ar.experience.content

class ContentModel(
    val contentTitle: String,
    val contentCategoryId: List<String>,
    val contentPairs: List<ContentPair>
)

class ContentPair(
    val label: String,
    val value: String
)