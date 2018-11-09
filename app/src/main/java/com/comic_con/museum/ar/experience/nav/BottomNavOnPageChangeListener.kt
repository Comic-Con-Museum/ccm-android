package com.comic_con.museum.ar.experience.nav

import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.MenuItem

class BottomNavOnPageChangeListener(
    private val bottomNavBar: BottomNavigationView
): ViewPager.OnPageChangeListener {

    private var prevMenuItem: MenuItem? = null

    override fun onPageScrollStateChanged(p0: Int) { /* Intentionally blank */ }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) { /* Intentionally blank */ }

    override fun onPageSelected(p0: Int) {
        if (prevMenuItem != null) {
            prevMenuItem?.isChecked = false
        } else {
            bottomNavBar.menu.getItem(0).isChecked = false
        }

        bottomNavBar.menu.getItem(p0).isChecked = true
        prevMenuItem = bottomNavBar.menu.getItem(p0);
    }
}