package com.comic_con.museum.ar.experience.progress

typealias ContentItemId = String

class ProgressModel(
    val progressItems: List<Progress>,
    val achievedContentItems: MutableList<ContentItemId>
)

class Progress(
    val progressId: String,
    val progressName: String,
    val contentItems: List<ContentItemId>
)