package com.comic_con.museum.ar.injection

import com.comic_con.museum.ar.MainActivity
import com.comic_con.museum.ar.experience.ExperienceActivity
import com.comic_con.museum.ar.experience.ExperienceFragment
import com.comic_con.museum.ar.experience.content.ContentActivity
import com.comic_con.museum.ar.experience.content.activityfragments.ContentListingFragment
import com.comic_con.museum.ar.experience.content.ContentOverviewFragment
import com.comic_con.museum.ar.experience.progress.ProgressFragment
import com.comic_con.museum.ar.experience.progress.ProgressViewModel
import com.comic_con.museum.ar.injection.sharedpreferences.SharedPreferencesModule
import com.comic_con.museum.ar.loading.LoadingScreenFragment
import com.comic_con.museum.ar.overview.OverviewFragment
import com.comic_con.museum.ar.views.ProgressView
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[CCMInjector::class,ContextModule::class, SharedPreferencesModule::class])
interface CCMComponent {

    // View Model Injection
    fun inject(f: OverviewFragment)

    fun inject(f: LoadingScreenFragment)

    fun inject(f: MainActivity)

    fun inject(a: ExperienceActivity)

    fun inject(f: ExperienceFragment)

    fun inject(f: ProgressFragment)

    fun inject(f: ContentOverviewFragment)

    fun inject(f: ContentListingFragment)

    fun inject(f: ContentActivity)

    fun inject(v: ProgressView)

    // Context Injection
    fun inject(module: SharedPreferencesModule)

    // Shared Preferences Injection
    fun inject(vm: ProgressViewModel)

}