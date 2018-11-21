package com.comic_con.museum.ar.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import com.comic_con.museum.ar.CCMApplication
import com.comic_con.museum.ar.R
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

        overviewViewModel.exhibitModelsLiveData.observeForever(this::initCarousel)

        return thisView
    }

    private fun initCarousel(exhibits: List<ExhibitModel>?) {
        exhibits ?: return

        // Initialize the carousel counter at the bottom of the view
        rootView?.findViewById<ViewGroup>(R.id.carousel_counters)?.let { holder ->
            holder.removeAllViews()
            (0..exhibits.size).forEach { index ->
                val newCounter = ImageView(context ?: return)
                val params = ViewGroup.LayoutParams(WRAP_CONTENT, MATCH_PARENT)
                newCounter.layoutParams = params
                newCounter.setImageDrawable(context?.getDrawable(R.drawable.carousel_counter_inactive))
                newCounter.adjustViewBounds = true
                holder.addView(newCounter)
            }
        }

        // Initialize the carousel contents
        val experienceCarousel = this.rootView?.findViewById<DiscreteScrollView>(R.id.experience_carousel) ?: return
        experienceCarousel.addOnItemChangedListener{ _, pos ->
            rootView?.findViewById<ViewGroup>(R.id.carousel_counters)?.let { holder ->
                (0..holder.childCount).forEach { index ->
                    (holder.getChildAt(index) as? ImageView)?.setImageDrawable(context?.getDrawable(
                        if( pos == index ) {
                            R.drawable.carousel_counter_active
                        } else {
                            R.drawable.carousel_counter_inactive
                        }
                    ))
                }
            }
        }
        val carouselAdapter = CarouselAdapter(exhibits)
        experienceCarousel.adapter = carouselAdapter
        // Start at the end of the carousel
        experienceCarousel.scrollToPosition(exhibits.size)
    }

}