package com.comic_con.museum.ar.experience

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comic_con.museum.ar.CCMApplication
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.content.ContentOverviewFragment
import com.comic_con.museum.ar.experience.launchar.LaunchArFragment
import com.comic_con.museum.ar.experience.progress.ProgressFragment
import com.comic_con.museum.ar.overview.ExperienceModel
import javax.inject.Inject

class ExperienceFragment: Fragment() {

    private var viewPager: ViewPager? = null

    // Fragments rendered on the viewPager
    private var progressFragment: ProgressFragment? = null
    private var galleryFragment: ContentOverviewFragment? = null
    private var launchArFragment: LaunchArFragment? = null

    @Inject
    lateinit var experienceViewModel: ExperienceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CCMApplication.getApplication().injectorComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewPager = inflater.inflate(R.layout.fragment_experience, container, false) as? ViewPager
        this.viewPager = viewPager
        // Setup fragments
        progressFragment = ProgressFragment()
        galleryFragment = ContentOverviewFragment()
        launchArFragment = LaunchArFragment()

        return viewPager
    }

    override fun onResume() {
        super.onResume()

        setupViewPager(viewPager)
        experienceViewModel.experienceModelLiveData.observeForever(this::updateFromExperienceModel)
    }

    override fun onPause() {
        super.onPause()

        experienceViewModel.experienceModelLiveData.removeObservers(this)
    }

    /**
     * Updates the fragment from the experienceModel we received from the overview
     */
    private fun updateFromExperienceModel(experienceModel: ExperienceModel?) {
        progressFragment?.experienceId = experienceModel?.id
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        viewPager ?: return

        // Determines the order of fragments
        val fragmentPages = listOf(
            progressFragment ?: return,
            galleryFragment ?: return,
            launchArFragment ?: return
        )
        // Create, setup, and set adapter
        val adapter = ViewPagerAdapter(this.childFragmentManager)
        adapter.fragments = fragmentPages
        viewPager.adapter = adapter
        // Start with the middle page
        viewPager.currentItem = 1
    }

    fun finishLoadingAr() {
        this.launchArFragment?.finishLoading()
    }

    inner class ViewPagerAdapter(fm: FragmentManager?): FragmentPagerAdapter(fm) {

        var fragments: List<Fragment> = listOf()

        override fun getCount() = fragments.size

        override fun getItem(p0: Int) = fragments[p0]

    }
}