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
import com.comic_con.museum.ar.CCMApplication
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.nav.BottomNavListener
import com.comic_con.museum.ar.experience.nav.BottomNavOnPageChangeListener
import com.comic_con.museum.ar.overview.OverviewViewModel
import com.comic_con.museum.ar.views.ExhibitCard
import com.unity3d.player.UnityPlayer
import java.lang.IllegalStateException
import javax.inject.Inject


class ExperienceActivity: AppCompatActivity() {

    var toolbar: ActionBar? = null

    @Inject
    lateinit var experienceViewModel: ExperienceViewModel

    @Inject
    lateinit var overviewViewModel: OverviewViewModel

    // The unity player for the AR component
    val unityPlayer: UnityPlayer by lazy {
        UnityPlayer(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CCMApplication.getApplication().injectorComponent.inject(this)

        // Get the experience model associated with the selected experience
        val experienceId = intent?.extras?.getString(ExhibitCard.EXPERIENCE_ID_KEY) ?: throw IllegalStateException("Experience was started with null experienceId")
        val experienceModel = overviewViewModel.exhibitModelsLiveData.value?.filter { model ->
            model.exhibitId == experienceId
        }?.getOrNull(0) ?: throw IllegalStateException("Experience was started with no valid experience model. ExperienceId: $experienceId")
        experienceViewModel.setExperience(experienceModel)

        // Set up the toolbar
        toolbar = supportActionBar
        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.show()

        setContentView(R.layout.activity_experiences)

        val frag = ExperienceFragment()
        frag.experienceViewModel = experienceViewModel
        switchToFragment(frag, "Experience")
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