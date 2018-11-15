package com.comic_con.museum.ar.injection

import com.comic_con.museum.ar.overview.OverviewFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules= [CCMInjector::class])
interface CCMComponent {

    fun inject(f: OverviewFragment)
}