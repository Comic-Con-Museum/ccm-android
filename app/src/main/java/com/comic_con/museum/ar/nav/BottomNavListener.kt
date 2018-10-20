package com.comic_con.museum.ar.nav

import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.comic_con.museum.ar.MainActivity
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.content.ContentFragment
import com.comic_con.museum.ar.launchar.LaunchArFragment
import com.comic_con.museum.ar.progress.ProgressFragment

class BottomNavListener(private val activity: MainActivity): BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        return when(p0.itemId) {
            R.id.progress -> moveToProgressFragment()
            R.id.content -> moveToContentFragment()
            R.id.launch_ar -> moveToLaunchArFragment()
            else -> false
        }
    }

    private fun moveToProgressFragment(): Boolean {
        activity.switchToFragment(ProgressFragment(), MainActivity.PROGRESS_TAG)
        return true
    }

    private fun moveToContentFragment(): Boolean {
        activity.switchToFragment(ContentFragment(), MainActivity.CONTENT_TAG)
        return true
    }

    private fun moveToLaunchArFragment(): Boolean {
        activity.switchToFragment(LaunchArFragment(), MainActivity.LAUNCH_AR_TAG)
        return true
    }
}