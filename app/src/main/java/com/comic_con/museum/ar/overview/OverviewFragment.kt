package com.comic_con.museum.ar.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comic_con.museum.ar.R
import com.yarolegovich.discretescrollview.DiscreteScrollView

class OverviewFragment: Fragment() {

    private var rootView: View? = null

    @Suppress("PrivatePropertyName")
    private var EXHIBIT_MODELS = listOf(
        ExhibitModel(
            "Eisners",
            "eisners",
            "The eisners are an award collection representing the best of the graphic novel industry. Including popular titles such as Monstress, Starman, and The Watchmen."
        ),
        ExhibitModel(
            "Eisners",
            "not eisners",
            "The eisners are an award collection representing the best of the graphic novel industry. Including popular titles such as Monstress, Starman, and The Watchmen."
        )
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val thisView = inflater.inflate(R.layout.fragment_overview, container, false)
        this.rootView = thisView

        val experienceCarousel = thisView.findViewById<DiscreteScrollView>(R.id.experience_carousel)

        val carouselAdapter = CarouselAdapter(EXHIBIT_MODELS)
        experienceCarousel?.adapter = carouselAdapter

        return thisView
    }
}