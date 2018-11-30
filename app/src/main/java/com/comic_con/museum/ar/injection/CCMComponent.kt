package com.comic_con.museum.ar.injection

import com.comic_con.museum.ar.MainActivity
import com.comic_con.museum.ar.experience.ExperienceActivity
import com.comic_con.museum.ar.experience.content.ContentBaseFragment
import com.comic_con.museum.ar.experience.content.subfragments.ContentOverviewFragment
import com.comic_con.museum.ar.experience.progress.ProgressFragment
import com.comic_con.museum.ar.experience.progress.ProgressViewModel
import com.comic_con.museum.ar.injection.sharedpreferences.SharedPreferencesModule
import com.comic_con.museum.ar.loading.LoadingScreenFragment
import com.comic_con.museum.ar.overview.OverviewFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[CCMInjector::class,ContextModule::class, SharedPreferencesModule::class])
interface CCMComponent {

    // View Model Injection
    fun inject(f: OverviewFragment)

    fun inject(f: ContentBaseFragment)

    fun inject(f: LoadingScreenFragment)

    fun inject(f: MainActivity)

    fun inject(a: ExperienceActivity)

    fun inject(f: ProgressFragment)

    fun inject(f: ContentOverviewFragment)

    // Context Injection
    fun inject(module: SharedPreferencesModule)

    // Shared Preferences Injection
    fun inject(vm: ProgressViewModel)

}