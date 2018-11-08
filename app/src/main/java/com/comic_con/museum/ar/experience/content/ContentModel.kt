package com.comic_con.museum.ar.experience.content

class ContentModel(
    val experienceId: String,
    val categories: List<Category>,
    val contentItems: List<Content>
)

class Category(
    val categoryId: String,
    val categoryTitle: String,
    val categoryDescription: String,
    val categoryImage: String
)

class Content(
    val contentTitle: String,
    val contentCategoryId: String,
    val contentJSON: String
)