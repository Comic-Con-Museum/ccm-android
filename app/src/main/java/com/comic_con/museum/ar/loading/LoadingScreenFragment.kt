package com.comic_con.museum.ar.loading

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.comic_con.museum.ar.CCMApplication
import com.comic_con.museum.ar.LaunchActivity
import com.comic_con.museum.ar.R
import javax.inject.Inject

class LoadingScreenFragment: Fragment() {

    @Inject
    lateinit var loadingScreenViewModel: LoadingScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CCMApplication.getApplication().injectorComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val thisView = inflater.inflate(R.layout.fragment_loading_screen, container, false)

        loadingScreenViewModel.initLoading().observeForever(this::completeLoading)

        val loadingImageView = thisView?.findViewById<ImageView>(R.id.loading_image) ?: return thisView

        context?.let { nonNullContext ->
            Glide.with(nonNullContext)
                .load(R.raw.loading)
                .into(loadingImageView)
        }

        return thisView
    }

    private fun completeLoading(isComplete: Boolean?) {
        if( isComplete == true ) {
            (activity as? LaunchActivity)?.finishLoading()
        }
    }
}