package com.comic_con.museum.ar.experience

import android.arch.lifecycle.MutableLiveData
import com.comic_con.museum.ar.overview.ContentItem
import com.comic_con.museum.ar.overview.ExhibitModel
import com.comic_con.museum.ar.persistence.Repository
import java.lang.IllegalStateException
import javax.inject.Inject

class ExperienceViewModel @Inject constructor(repository: Repository) {

    val experienceModelLiveData: MutableLiveData<ExhibitModel> = MutableLiveData()

    fun setExperience(model: ExhibitModel) {
        experienceModelLiveData.postValue(model)
    }

    fun getSpecificContent(experienceModel: ExhibitModel, contentItemId: String): ContentItem? {
        return experienceModel.category.categories.union(experienceModel.content.contentItems).find { contentItem ->
            contentItem.id == contentItemId
        }
    }

    fun isCategory(experienceModel: ExhibitModel, contentItemId: String): Boolean {
        return experienceModel.category.categories.asSequence().map { it.id }.contains(contentItemId)
    }
}