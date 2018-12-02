package com.comic_con.museum.ar.experience

import android.arch.lifecycle.MutableLiveData
import com.comic_con.museum.ar.overview.ExperienceModel
import com.comic_con.museum.ar.persistence.Repository
import javax.inject.Inject

class ExperienceViewModel @Inject constructor(repository: Repository) {

    val experienceModelLiveData: MutableLiveData<ExperienceModel> = MutableLiveData()

    fun setExperience(model: ExperienceModel) {
        experienceModelLiveData.postValue(model)
    }
}