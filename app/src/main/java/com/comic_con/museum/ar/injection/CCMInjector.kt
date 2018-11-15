package com.comic_con.museum.ar.injection

import com.comic_con.museum.ar.overview.OverviewViewModel
import com.comic_con.museum.ar.persistence.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CCMInjector(private val repository: Repository) {

    @Provides
    @Singleton
    fun providesOverviewViewModel(): OverviewViewModel {
        return OverviewViewModel(this.repository)
    }
}