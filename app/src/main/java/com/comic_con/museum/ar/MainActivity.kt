package com.comic_con.museum.ar

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import com.comic_con.museum.ar.nav.BottomNavListener

class MainActivity: AppCompatActivity() {

    var toolbar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar = supportActionBar

        setContentView(R.layout.content_view)

        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavBar?.setOnNavigationItemSelectedListener(BottomNavListener(this))

    }

    fun switchToFragment(fragment: Fragment, tag: String?) {
        val transaction = supportFragmentManager?.beginTransaction() ?: return
        transaction.replace(R.id.content_frame, fragment)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

    companion object {

        const val CONTENT_TAG = "Content"
        const val PROGRESS_TAG = "Progress"
        const val LAUNCH_AR_TAG = "LaunchAr"
    }
}