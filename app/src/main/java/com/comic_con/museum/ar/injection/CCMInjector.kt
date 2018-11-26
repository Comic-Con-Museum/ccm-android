package com.comic_con.museum.ar.injection

import com.comic_con.museum.ar.experience.ExperienceViewModel
import com.comic_con.museum.ar.experience.progress.ProgressViewModel
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
    fun providesOverviewViewModel() = OverviewViewModel(this.repository)

    @Provides
    @Singleton
    fun providesLoadingScreenViewModel() = LoadingScreenViewModel(this.repository)

    @Provides
    @Singleton
    fun providesProgressViewModel() = ProgressViewModel(this.repository)

    @Provides
    fun providesExperienceViewModel() = ExperienceViewModel(this.repository)

}