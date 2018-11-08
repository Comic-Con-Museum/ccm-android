package com.comic_con.museum.ar.experience.launchar

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.ExperienceActivity

class LaunchArFragment: Fragment() {

    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_launch_ar, container, false)
        this.rootView = rootView

        rootView?.findViewById<Button>(R.id.launch_ar_button)?.setOnClickListener {
            (this.activity as? ExperienceActivity)?.switchToAR()
        }

        return rootView
    }
}