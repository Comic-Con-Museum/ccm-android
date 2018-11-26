package com.comic_con.museum.ar.injection.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class SharedPreferencesModule {

    @Suppress("PrivatePropertyName")
    private val SHARED_PREFERENCES_NAME = "CCMPreferences"

    @Provides
    @Singleton
    @Inject
    fun provideSharedPreferences (context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
}