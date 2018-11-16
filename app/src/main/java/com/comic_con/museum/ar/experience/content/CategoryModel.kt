package com.comic_con.museum.ar.experience.content

class CategoryModel(
    val experienceId: String,
    val categories: List<Category>
)

class Category(
    val categoryId: String,
    val categoryTitle: String,
    val categoryDescription: String,
    val categoryImage: String
)