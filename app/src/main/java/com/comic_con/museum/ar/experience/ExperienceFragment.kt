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
import com.comic_con.museum.ar.experience.content.ContentFragment
import com.comic_con.museum.ar.experience.launchar.LaunchArFragment
import com.comic_con.museum.ar.experience.progress.ProgressFragment

class ExperienceFragment: Fragment() {

    private var viewPager: ViewPager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewPager = inflater.inflate(R.layout.fragment_experience, container, false) as? ViewPager
        this.viewPager = viewPager
        return viewPager
    }

    override fun onResume() {
        super.onResume()
        setupViewPager(this.viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        viewPager ?: return

        // Determines the order of fragments
        val fragmentPages = listOf(
            ProgressFragment(),
            ContentFragment(),
            LaunchArFragment()
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