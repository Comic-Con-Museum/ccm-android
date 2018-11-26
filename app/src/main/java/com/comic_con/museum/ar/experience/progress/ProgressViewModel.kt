package com.comic_con.museum.ar.experience.progress

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import android.os.AsyncTask
import com.comic_con.museum.ar.CCMApplication
import com.comic_con.museum.ar.injection.sharedpreferences.SharedPreferenceUtil
import com.comic_con.museum.ar.persistence.Repository
import javax.inject.Inject

class ProgressViewModel @Inject constructor(repository: Repository) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val progressLiveDataFields = HashMap<String, MutableLiveData<ProgressModel>>()

    init {
        CCMApplication.getApplication().injectorComponent.inject(this)
    }

    fun getExperienceProgressLiveData(experienceId: String, initialModel: ProgressModel?): LiveData<ProgressModel> {
        // Get the new live data associated with the experience progress or create it here
        val thisExperienceLiveData = progressLiveDataFields.getOrElse(experienceId) {
            val newLiveData = MutableLiveData<ProgressModel>()
            progressLiveDataFields[experienceId] = newLiveData
            newLiveData
        }
        // Begin loading the experience progress into the live data if it hasn't already been loaded
        initialModel?.let {
            loadProgress(experienceId, initialModel)
        }

        return thisExperienceLiveData

    }

    fun updateProgress(experienceId: String, contentId: String) {
        val thisExperienceLiveData = progressLiveDataFields.getOrElse(experienceId) { return }
        val thisExperienceModel = thisExperienceLiveData.value
        if( thisExperienceModel?.achievedContentItems?.contains(contentId) == false ) {
            thisExperienceModel.achievedContentItems.add(contentId)
        }
        // Post the value to update the view model
        thisExperienceLiveData.postValue(thisExperienceModel)
        // Save the progress
        saveProgress(experienceId)
    }

    fun saveProgress(experienceId: String) {
        AsyncTask.execute { saveProgressSync(experienceId) }
    }

    private fun loadProgress(experienceId: String, initialModel: ProgressModel) {
        AsyncTask.execute{ loadProgressSync(experienceId, initialModel) }
    }

    @Synchronized
    private fun saveProgressSync(experienceId: String) {
        val thisExperienceModel = progressLiveDataFields.getOrElse(experienceId) { null }?.value ?: return
        sharedPreferences
            .edit()
            .putString(experienceId, SharedPreferenceUtil.progressModelToPersistableString(thisExperienceModel))
            .apply()
    }

    @Synchronized
    fun loadProgressSync(experienceId: String, initialModel: ProgressModel) {
        // Get persisted string from shared preferences
        val persistedString = sharedPreferences.getString(experienceId, null)
        // Parse and format
        val experienceProgress: ProgressModel? = SharedPreferenceUtil.persistableStringToProgressModel(persistedString)
        // Post value to live data
        progressLiveDataFields[experienceId]?.postValue(experienceProgress ?: initialModel)
    }
}