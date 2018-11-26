package com.comic_con.museum.ar.overview

import com.comic_con.museum.ar.experience.content.CategoryModel
import com.comic_con.museum.ar.experience.content.ContentModel
import com.comic_con.museum.ar.experience.progress.ProgressModel

class ExhibitModel(
    val exhibitTitle: String,
    val exhibitId: String,
    val exhibitDescription: String,
    val exhibitAdditionDescription: String,
    val exhibitImageUrl: String,
    val category: CategoryModel,
    val content: ContentModel,
    val progress: ProgressModel
)