package com.comic_con.museum.ar.experience.nav

import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.ExperienceActivity
import com.comic_con.museum.ar.experience.content.ContentFragment
import com.comic_con.museum.ar.experience.launchar.LaunchArFragment
import com.comic_con.museum.ar.experience.progress.ProgressFragment

class BottomNavListener(private val activity: ExperienceActivity): BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        return when(p0.itemId) {
            R.id.progress -> moveToProgressFragment()
            R.id.content -> moveToContentFragment()
            R.id.launch_ar -> moveToLaunchArFragment()
            else -> false
        }
    }

    private fun moveToProgressFragment(): Boolean {
        activity.switchToFragment(ProgressFragment(), ExperienceActivity.PROGRESS_TAG)
        return true
    }

    private fun moveToContentFragment(): Boolean {
        activity.switchToFragment(ContentFragment(), ExperienceActivity.CONTENT_TAG)
        return true
    }

    private fun moveToLaunchArFragment(): Boolean {
        activity.switchToFragment(LaunchArFragment(), ExperienceActivity.LAUNCH_AR_TAG)
        return true
    }
}