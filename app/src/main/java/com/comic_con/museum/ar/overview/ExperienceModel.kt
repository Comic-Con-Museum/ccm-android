package com.comic_con.museum.ar.overview

import android.os.Parcelable
import com.comic_con.museum.ar.experience.progress.ProgressModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class ExperienceModel(
    val title: String,
    val id: String,
    val description: String,
    val additionalDescription: String,
    val imageUrl: String,
    val contentTags: List<String>,
    val category: CategoryModel,
    val content: ContentModel,
    val progress: ProgressModel
)

class CategoryModel(
    val categories: List<ContentItem>
)

class ContentModel(
    val contentItems: List<ContentItem>
)

@Parcelize
class ContentItem(
    val title: String,
    val id: String,
    val description: String,
    val imageUrl: String,
    val tags: List<String>,
    val categories: List<String>?,
    val extraPairs: List<ContentPair>?
): Parcelable

@Parcelize
class ContentPair(
    val label: String,
    val value: String,
    val contentReferenceId: String,
    val priority: Priority
): Parcelable

enum class Priority{
    @SerializedName("1")
    PRIMARY,
    @SerializedName("2")
    SECONDARY
}