package com.comic_con.museum.ar.overview

import com.comic_con.museum.ar.experience.content.CategoryModel
import com.comic_con.museum.ar.experience.content.ContentModel
import com.comic_con.museum.ar.experience.progress.ProgressModel

class ExperienceModel(
    val experienceTitle: String,
    val experienceId: String,
    val experienceDescription: String,
    val experienceAdditionDescription: String,
    val experienceImageUrl: String,
    val category: CategoryModel,
    val content: ContentModel,
    val progress: ProgressModel
)