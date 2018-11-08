package com.comic_con.museum.ar.experience.progress

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.views.ProgressGridView

class ProgressFragment: Fragment() {

    private var rootView: View? = null

    private val PROGRESS_MODEL = ProgressModel(
        "eisners",
        listOf(
            Progress(
                "1",
                "Challenge 1",
                5f, 1f
            ),
            Progress(
                "2",
                "Challenge 2",
                5f, 5f
            ),
            Progress(
                "3",
                "Challenge 3",
                5f, 5f
            ),
            Progress(
                "4",
                "Challenge 4",
                2f, 1f
            )
        )
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_progress, container, false)
        this.rootView = rootView

        rootView?.findViewById<ProgressGridView>(R.id.progress_grid)?.setUp(PROGRESS_MODEL)

        return rootView
    }
}