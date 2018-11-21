package com.comic_con.museum.ar.overview

import com.comic_con.museum.ar.experience.content.CategoryModel
import com.comic_con.museum.ar.experience.content.ContentModel

class ExhibitModel(
    val exhibitTitle: String,
    val exhibitId: String,
    val exhibitDescription: String,
    val category: CategoryModel,
    val content: ContentModel
)