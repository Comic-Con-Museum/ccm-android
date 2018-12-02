package com.comic_con.museum.ar.experience.progress

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comic_con.museum.ar.CCMApplication
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.views.ProgressListView
import javax.inject.Inject

class ProgressFragment: Fragment() {

    var experienceId: String? = null
    set(value) {
        field = value
        setUpProgressModel()
    }

    private var rootView: View? = null

    @Inject
    lateinit var progressViewModel: ProgressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CCMApplication.getApplication().injectorComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_progress, container, false)
        this.rootView = rootView

        setUpProgressModel()

        return rootView
    }

    private fun setUpProgressModel() {
        try {
            experienceId?.let { experienceId ->
                progressViewModel.getExperienceProgressLiveData(experienceId, null)
                    .observeForever(this::updateFromProgressModel)
            }
        } catch (e: UninitializedPropertyAccessException) { /* Ignore, as the attempted operation will be completed later */ }
    }

    private fun updateFromProgressModel(progressModel: ProgressModel?) {
        progressModel?.let {
            rootView?.findViewById<ProgressListView>(R.id.progress_list)?.setUp(it)
        }
    }
}