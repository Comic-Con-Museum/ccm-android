package com.comic_con.museum.ar.injection

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {

    @Provides
    fun provideContext() = this.context
}