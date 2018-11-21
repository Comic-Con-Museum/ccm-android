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
import com.comic_con.museum.ar.experience.nav.BottomNavOnPageChangeListener
import com.unity3d.player.UnityPlayer


class ExperienceActivity: AppCompatActivity() {

    var toolbar: ActionBar? = null

    // The unity player for the AR component
    val unityPlayer: UnityPlayer by lazy {
        UnityPlayer(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar = supportActionBar

        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.show()

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

    /**
     * Called from Unity when a new item is collected
     */
    @Suppress("unused")
    fun newCollectionEvent(contentId: String): Int {
        return 0
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

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        unityPlayer.windowFocusChanged(hasFocus)
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, ExperienceActivity::class.java)
        }
    }
}