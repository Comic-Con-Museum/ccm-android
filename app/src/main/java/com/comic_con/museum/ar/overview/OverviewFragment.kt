package com.comic_con.museum.ar.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.ExperienceActivity

class OverviewFragment: Fragment() {

    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val thisView = inflater.inflate(R.layout.fragment_overview, container, false)
        this.rootView = thisView

        this.context?.let { thisContext ->
            thisView?.findViewById<Button>(R.id.button)?.setOnClickListener {
                startActivity(ExperienceActivity.createIntent(thisContext))
            }
        }

        return thisView
    }
}