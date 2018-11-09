package com.comic_con.museum.ar.experience.nav

import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.comic_con.museum.ar.R

class BottomNavListener(private val viewPager: ViewPager): BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        return when(p0.itemId) {
            R.id.progress -> moveToProgressFragment()
            R.id.content -> moveToContentFragment()
            R.id.launch_ar -> moveToLaunchArFragment()
            else -> false
        }
    }

    private fun moveToProgressFragment(): Boolean {
        viewPager.currentItem = 0
        return true
    }

    private fun moveToContentFragment(): Boolean {
        viewPager.currentItem = 1
        return true
    }

    private fun moveToLaunchArFragment(): Boolean {
        viewPager.currentItem = 2
        return true
    }
}