package com.comic_con.museum.ar.experience

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.content.ContentFragment
import com.comic_con.museum.ar.experience.nav.BottomNavListener
import android.widget.Toast
import com.comic_con.museum.ar.ar.UnityCompatActivity


class ExperienceActivity: AppCompatActivity() {

    var toolbar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar = supportActionBar

        toolbar?.setDisplayHomeAsUpEnabled(true)

        setContentView(R.layout.activity_experiences)

        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavBar?.setOnNavigationItemSelectedListener(BottomNavListener(this))
        bottomNavBar?.selectedItemId = R.id.content

        switchToFragment(ContentFragment(), CONTENT_TAG)
    }


    fun switchToFragment(fragment: Fragment, tag: String?) {
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

    fun switchToAR() {
        val arIntent = Intent(this, UnityCompatActivity::class.java)
        startActivity(arIntent)
    }

    companion object {

        const val CONTENT_TAG = "Content"
        const val PROGRESS_TAG = "Progress"
        const val LAUNCH_AR_TAG = "LaunchAr"

        fun createIntent(context: Context): Intent {
            return Intent(context, ExperienceActivity::class.java)
        }
    }
}