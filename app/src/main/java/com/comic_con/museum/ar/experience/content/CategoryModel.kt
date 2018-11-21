package com.comic_con.museum.ar.experience.content

class CategoryModel(
    val categories: List<Category>,
    val allTags: List<String>
)

class Category(
    val categoryId: String,
    val categoryTitle: String,
    val categoryDescription: String,
    val categoryTags: List<String>,
    val categoryImage: String
)