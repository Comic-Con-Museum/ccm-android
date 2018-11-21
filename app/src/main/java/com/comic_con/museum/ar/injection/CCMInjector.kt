package com.comic_con.museum.ar.injection

import com.comic_con.museum.ar.experience.ExperienceViewModel
import com.comic_con.museum.ar.loading.LoadingScreenViewModel
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

    @Provides
    @Singleton
    fun providesLoadingScreenViewModel(): LoadingScreenViewModel {
        return LoadingScreenViewModel(this.repository)
    }

    @Provides
    fun providesExperienceViewModel(): ExperienceViewModel {
        return ExperienceViewModel(this.repository)
    }
}