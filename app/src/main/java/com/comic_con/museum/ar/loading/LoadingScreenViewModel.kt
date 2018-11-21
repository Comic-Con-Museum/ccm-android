package com.comic_con.museum.ar.loading

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.Handler
import com.comic_con.museum.ar.persistence.Repository
import javax.inject.Inject

class LoadingScreenViewModel @Inject constructor(repository: Repository) {

    private val loadingCompletedLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun initLoading(): LiveData<Boolean> {
        Handler().postDelayed({
            finishLoading()
        }, 5000)
        return this.loadingCompletedLiveData
    }

    private fun finishLoading() {
        this.loadingCompletedLiveData.postValue(true)
    }
}