package com.comic_con.museum.ar.persistence

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.AsyncTask
import android.util.Log
import com.comic_con.museum.ar.persistence.responses.WebResponsePayload
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.OkHttpClient

class Repository {

    private val gson by lazy {
        Gson()
    }

    private val okHttpClient by lazy {
        OkHttpClient()
    }

    fun makeRequest(request: WebRequest): LiveData<WebResponsePayload> {
        val thisCallLiveData: MutableLiveData<WebResponsePayload> = MutableLiveData()

        AsyncTask.execute {
            val response = okHttpClient.newCall(request.buildOkHttpRequest()).execute()
            val rawResponse = response.body()?.string()
            try {
                // If success
                if (response.code() in (200..299)) {
                    val parsedResponse = gson.fromJson(rawResponse, request.responseClass)
                    thisCallLiveData.postValue(parsedResponse)
                } else {
                    // TODO handle error
                }
            } catch (e: JsonSyntaxException) {
                Log.e("JSONError", "Failure to parse JSON object as ${request.responseClass}: $rawResponse")
            }
        }

        return thisCallLiveData
    }
}