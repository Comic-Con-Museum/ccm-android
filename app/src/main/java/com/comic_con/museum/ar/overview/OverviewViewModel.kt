package com.comic_con.museum.ar.overview

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.comic_con.museum.ar.experience.content.CategoryModel
import com.comic_con.museum.ar.experience.content.ContentModel
import com.comic_con.museum.ar.persistence.Repository
import javax.inject.Inject

class OverviewViewModel @Inject constructor(private val repository: Repository) {

    // Live Data
    val exhibitModelsLiveData: MutableLiveData<List<ExhibitModel>> = MutableLiveData()

    @Suppress("PrivatePropertyName")
    private var EXHIBIT_MODELS = listOf(
        ExhibitModel(
            "Eisners",
            "eisners",
            "The eisners are an award collection representing the best of the graphic novel industry. Including popular titles such as Monstress, Starman, and The Watchmen.",
            CategoryModel(listOf()),
            ContentModel("asd", "asd", listOf(), listOf())
        ),
        ExhibitModel(
            "Eisners",
            "not eisners",
            "The eisners are an award collection representing the best of the graphic novel industry. Including popular titles such as Monstress, Starman, and The Watchmen.",
            CategoryModel(listOf()),
            ContentModel("asd", "asd", listOf(), listOf())
        )
    )

    init {
        exhibitModelsLiveData.postValue(EXHIBIT_MODELS)
    }
}