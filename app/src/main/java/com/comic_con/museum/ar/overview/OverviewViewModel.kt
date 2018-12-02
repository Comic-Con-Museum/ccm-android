package com.comic_con.museum.ar.overview

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.comic_con.museum.ar.persistence.Repository
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.InputStream
import javax.inject.Inject

class OverviewViewModel @Inject constructor(private val repository: Repository) {

    // Live Data for the experiences
    val experienceModelsLiveData: MutableLiveData<List<ExperienceModel>> = MutableLiveData()
    // All the experiences loaded so far
    private val models: MutableList<ExperienceModel> = mutableListOf()
    // Mapping of models to associated android resource ID
    private val modelResMap: HashMap<String, Int> = hashMapOf()
    // gson
    private val gson by lazy {
        Gson()
    }

    fun addExperienceModel(resId: Int, inputStream: InputStream) {
        try {
            val experienceModel = this.gson.fromJson(inputStream.bufferedReader(), ExperienceModel::class.java)
            if(
                models.find { thisModel ->
                    thisModel.id == experienceModel.id
                } == null
            ) {
                models.add(experienceModel)
            }
            experienceModelsLiveData.postValue(models)
            modelResMap[experienceModel.id] = resId
        } catch (e: JsonSyntaxException) {
            Log.e("JSON", "Failed to parse JSON object")
        }
    }

    fun getResId(experienceId: String): Int? {
        return modelResMap.getOrElse(experienceId) { return null }
    }
}