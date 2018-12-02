package com.comic_con.museum.ar.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.ListView
import com.comic_con.museum.ar.CCMApplication
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.views.ExperienceListView
import com.yarolegovich.discretescrollview.DiscreteScrollView
import javax.inject.Inject

class OverviewFragment: Fragment() {

    private var rootView: View? = null

    @Inject
    lateinit var overviewViewModel: OverviewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CCMApplication.getApplication().injectorComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val thisView = inflater.inflate(R.layout.fragment_overview, container, false)
        this.rootView = thisView

        overviewViewModel.experienceModelsLiveData.observeForever(this::initCarousel)

        return thisView
    }

    private fun initCarousel(experiences: List<ExperienceModel>?) {
        experiences ?: return

        this.rootView?.findViewById<ExperienceListView>(R.id.experience_list)?.let { experienceList ->
            experienceList.setUp(experiences)
        }
    }

}