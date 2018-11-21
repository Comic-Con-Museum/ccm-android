package com.comic_con.museum.ar.overview

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.comic_con.museum.ar.persistence.Repository
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.InputStream
import javax.inject.Inject

class OverviewViewModel @Inject constructor(private val repository: Repository) {

    // Live Data for the exhibits
    val exhibitModelsLiveData: MutableLiveData<List<ExhibitModel>> = MutableLiveData()
    // All the exhibits loaded so far
    private val models: MutableList<ExhibitModel> = mutableListOf()
    // gson
    private val gson by lazy {
        Gson()
    }

    fun addExhibitModel(inputStream: InputStream) {
        try {
            val exhibitModel = this.gson.fromJson(inputStream.bufferedReader(), ExhibitModel::class.java)
            if(
                models.find { thisModel ->
                    thisModel.exhibitId == exhibitModel.exhibitId
                } == null
            ) {
                models.add(exhibitModel)
            }
            exhibitModelsLiveData.postValue(models)
        } catch (e: JsonSyntaxException) {
            Log.e("JSON", "Failed to parse JSON object")
        }
    }
}