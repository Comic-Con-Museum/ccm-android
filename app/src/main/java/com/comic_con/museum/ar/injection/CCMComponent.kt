package com.comic_con.museum.ar.injection

import com.comic_con.museum.ar.MainActivity
import com.comic_con.museum.ar.experience.ExperienceActivity
import com.comic_con.museum.ar.experience.content.ContentFragment
import com.comic_con.museum.ar.loading.LoadingScreenFragment
import com.comic_con.museum.ar.overview.OverviewFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[CCMInjector::class])
interface CCMComponent {

    fun inject(f: OverviewFragment)

    fun inject(f: ContentFragment)

    fun inject(f: LoadingScreenFragment)

    fun inject(f: MainActivity)

    fun inject(f: ExperienceActivity)
}