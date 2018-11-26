package com.comic_con.museum.ar.experience.progress

typealias ContentItemId = String

class ProgressModel(
    val progressItems: List<Progress>
)

class Progress(
    val progressId: String,
    val progressName: String,
    val contentItems: List<ContentItemId>,
    val achievedContentItems: MutableList<ContentItemId>
)