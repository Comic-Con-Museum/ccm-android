package com.comic_con.museum.ar.experience

import android.arch.lifecycle.MutableLiveData
import com.comic_con.museum.ar.overview.ExhibitModel
import com.comic_con.museum.ar.persistence.Repository
import javax.inject.Inject

class ExperienceViewModel @Inject constructor(repository: Repository) {

    val experienceModelLiveData: MutableLiveData<ExhibitModel> = MutableLiveData()

    fun setExperience(model: ExhibitModel) {
        experienceModelLiveData.postValue(model)
    }
}