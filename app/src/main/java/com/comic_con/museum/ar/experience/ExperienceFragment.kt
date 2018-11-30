package com.comic_con.museum.ar.experience

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.content.ContentBaseFragment
import com.comic_con.museum.ar.experience.launchar.LaunchArFragment
import com.comic_con.museum.ar.experience.progress.ProgressFragment
import com.comic_con.museum.ar.overview.ExhibitModel

class ExperienceFragment: Fragment() {

    private var viewPager: ViewPager? = null

    // Fragments rendered on the viewPager
    private var progressFragment: ProgressFragment? = null
    private var contentBaseFragment: ContentBaseFragment? = null
    private var launchArFragment: LaunchArFragment? = null

    // Not injected, but set from the activity
    lateinit var experienceViewModel: ExperienceViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewPager = inflater.inflate(R.layout.fragment_experience, container, false) as? ViewPager
        this.viewPager = viewPager
        // Setup fragments
        progressFragment = ProgressFragment()
        contentBaseFragment = ContentBaseFragment()
        contentBaseFragment?.experienceViewModel = experienceViewModel
        launchArFragment = LaunchArFragment()

        return viewPager
    }

    override fun onResume() {
        super.onResume()

        setupViewPager(viewPager)
        experienceViewModel.experienceModelLiveData.observeForever(this::updateFromExhibitModel)
    }

    override fun onPause() {
        super.onPause()

        experienceViewModel.experienceModelLiveData.removeObservers(this)
    }

    /**
     * Updates the fragment from the exhibitModel we received from the overview
     */
    private fun updateFromExhibitModel(exhibitModel: ExhibitModel?) {
        progressFragment?.experienceId = exhibitModel?.id
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        viewPager ?: return

        // Determines the order of fragments
        val fragmentPages = listOf(
            progressFragment ?: return,
            contentBaseFragment ?: return,
            launchArFragment ?: return
        )
        // Create, setup, and set adapter
        val adapter = ViewPagerAdapter(this.childFragmentManager)
        adapter.fragments = fragmentPages
        viewPager.adapter = adapter
        // Start with the middle page
        viewPager.currentItem = 1
    }

    inner class ViewPagerAdapter(fm: FragmentManager?): FragmentPagerAdapter(fm) {

        var fragments: List<Fragment> = listOf()

        override fun getCount() = fragments.size

        override fun getItem(p0: Int) = fragments[p0]

    }
}