package com.comic_con.museum.ar.experience

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.MenuItem
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.nav.BottomNavListener
import com.comic_con.museum.ar.ar.UnityCompatActivity
import com.comic_con.museum.ar.experience.nav.BottomNavOnPageChangeListener


class ExperienceActivity: AppCompatActivity() {

    var toolbar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar = supportActionBar

        toolbar?.setDisplayHomeAsUpEnabled(true)

        setContentView(R.layout.activity_experiences)

        switchToFragment(ExperienceFragment(), "Experience")
    }

    override fun onResume() {
        super.onResume()

        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNav)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        bottomNavBar?.setOnNavigationItemSelectedListener(BottomNavListener(viewPager))
        viewPager.addOnPageChangeListener(BottomNavOnPageChangeListener(bottomNavBar))
    }


    private fun switchToFragment(fragment: Fragment, tag: String?) {
        val transaction = supportFragmentManager?.beginTransaction() ?: return
        transaction.replace(R.id.content_frame, fragment)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when( item.itemId ) {
            android.R.id.home -> this.finish()
        }
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish()
            true
        } else super.onKeyDown(keyCode, event)
    }

    fun switchToAR() {
        val arIntent = Intent(this, UnityCompatActivity::class.java)
        startActivity(arIntent)
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, ExperienceActivity::class.java)
        }
    }
}