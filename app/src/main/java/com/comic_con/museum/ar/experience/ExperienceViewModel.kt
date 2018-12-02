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

    fun getSpecificContent(contentItemId: String, experienceModel: ExhibitModel? = null): ContentItem? {
        val thisModel = experienceModel ?: this.experienceModelLiveData.value ?: return null
        return thisModel.category.categories.union(thisModel.content.contentItems).find { contentItem ->
            contentItem.id == contentItemId
        }
    }

    fun isCategory(contentItemId: String, experienceModel: ExhibitModel? = null): Boolean {
        val thisModel = experienceModel ?: this.experienceModelLiveData.value ?: return false
        return thisModel.category.categories.asSequence().map { it.id }.contains(contentItemId)
    }
}