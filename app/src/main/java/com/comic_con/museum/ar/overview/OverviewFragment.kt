package com.comic_con.museum.ar.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.ExperienceActivity
import com.comic_con.museum.ar.views.ExhibitCard

class OverviewFragment: Fragment() {

    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val thisView = inflater.inflate(R.layout.fragment_overview, container, false)
        this.rootView = thisView

        this.context?.let { thisContext ->
            thisView?.findViewById<ExhibitCard>(R.id.content_card)?.onContentSelected = {
                startActivity(ExperienceActivity.createIntent(thisContext))
            }
        }

        return thisView
    }
}