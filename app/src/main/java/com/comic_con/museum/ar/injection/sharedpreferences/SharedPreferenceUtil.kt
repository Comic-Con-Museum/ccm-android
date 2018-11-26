package com.comic_con.museum.ar.injection.sharedpreferences

import com.comic_con.museum.ar.experience.progress.ProgressModel
import com.google.gson.Gson
import com.google.gson.JsonParseException


class SharedPreferenceUtil{

    companion object {

        private val gson = Gson()

        fun progressModelToPersistableString(model: ProgressModel): String {
            return gson.toJson(model)
        }

        fun persistableStringToProgressModel(str: String?): ProgressModel? {
            return try {
                gson.fromJson<ProgressModel>(str, ProgressModel::class.java)
            } catch(e: JsonParseException) {
                null
            }
        }
    }
}