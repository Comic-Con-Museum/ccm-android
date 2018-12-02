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
import com.comic_con.museum.ar.overview.OverviewViewModel
import com.comic_con.museum.ar.util.GlideHelper
import javax.inject.Inject

class LoadingScreenFragment: Fragment() {

    @Inject
    lateinit var loadingScreenViewModel: LoadingScreenViewModel

    @Inject
    lateinit var overviewViewModel: OverviewViewModel

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

        beginContextDependentLoading()

        return thisView
    }

    private fun completeLoading(isComplete: Boolean?) {
        if( isComplete == true ) {
            (activity as? LaunchActivity)?.finishLoading()
        }
    }

    private fun beginContextDependentLoading() {
        overviewViewModel.addExperienceModel(R.raw.experience_eisners, resources.openRawResource(R.raw.experience_eisners))
        overviewViewModel.addExperienceModel(R.raw.experience_may_fourth, resources.openRawResource(R.raw.experience_may_fourth))

        // Preload overview images
        this.context?.let { context ->
            overviewViewModel.experienceModelsLiveData.value?.forEach { experienceModel ->
                GlideHelper.preloadImage(context, experienceModel.imageUrl)
            }
        }
    }
}