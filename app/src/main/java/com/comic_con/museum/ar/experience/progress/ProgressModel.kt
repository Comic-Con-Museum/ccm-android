package com.comic_con.museum.ar.experience.progress

class ProgressModel(
    val experienceId: String,
    val progressItems: List<Progress>
)

class Progress(
    val progressId: String,
    val progressName: String,
    val progressMax: Float,
    val progressAchieved: Float
)