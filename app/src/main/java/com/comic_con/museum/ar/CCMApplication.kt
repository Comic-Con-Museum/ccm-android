package com.comic_con.museum.ar

import android.app.Application
import com.comic_con.museum.ar.injection.CCMComponent
import com.comic_con.museum.ar.injection.CCMInjector
import com.comic_con.museum.ar.injection.DaggerCCMComponent
import com.comic_con.museum.ar.persistence.Repository

class CCMApplication: Application() {

    lateinit var injectorComponent: CCMComponent

    override fun onCreate() {
        super.onCreate()

        setApplication(this)

        injectorComponent = DaggerCCMComponent.builder()
            .cCMInjector(CCMInjector(Repository()))
            .build()
    }

    companion object {

        private lateinit var application: CCMApplication

        private fun setApplication(app: CCMApplication) { application = app }

        fun getApplication() = application
    }
}