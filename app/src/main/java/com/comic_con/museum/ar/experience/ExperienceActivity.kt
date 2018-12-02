package com.comic_con.museum.ar.experience

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.MenuItem
import com.comic_con.museum.ar.CCMApplication
import com.comic_con.museum.ar.MainActivity
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.nav.BottomNavListener
import com.comic_con.museum.ar.experience.nav.BottomNavOnPageChangeListener
import com.comic_con.museum.ar.experience.progress.ProgressViewModel
import com.comic_con.museum.ar.overview.ExperienceModel
import com.google.gson.Gson
import com.unity3d.player.UnityPlayer
import javax.inject.Inject


class ExperienceActivity: AppCompatActivity() {

    var toolbar: ActionBar? = null

    @Inject
    lateinit var experienceViewModel: ExperienceViewModel

    @Inject
    lateinit var progressViewModel: ProgressViewModel

    private var experienceModel: ExperienceModel? = null

    // The unity player for the AR component
    val unityPlayer: UnityPlayer by lazy {
        UnityPlayer(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable this to stop program until debugger attach
//        Debug.waitForDebugger()

        CCMApplication.getApplication().injectorComponent.inject(this)

        // Get the experience model associated with the selected experience
        val experienceRes = intent?.extras?.getInt(MainActivity.EXPERIENCE_RESOURCE_KEY) ?: throw IllegalStateException("Experience was started with null experienceId")
        val experienceModel = Gson().fromJson(resources.openRawResource(experienceRes).bufferedReader(), ExperienceModel::class.java)
        experienceViewModel.setExperience(experienceModel)
        this.experienceModel = experienceModel

        // Set activity title
        this.title = experienceModel.title

        // Set up the toolbar
        toolbar = supportActionBar
        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.show()

        // Set up the experience Progress ViewModel with the initial model if needed
        progressViewModel.getExperienceProgressLiveData(experienceModel.id, experienceModel.progress)

        setContentView(R.layout.activity_experiences)

        // Simulate receiving a collection event from Unity
        Handler().postDelayed({
            this.newCollectionEvent("00002")
        }, 5000)

        val frag = ExperienceFragment()
        switchToFragment(frag)
    }

    override fun onResume() {
        super.onResume()

        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNav)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        bottomNavBar?.setOnNavigationItemSelectedListener(BottomNavListener(viewPager))
        viewPager.addOnPageChangeListener(BottomNavOnPageChangeListener(bottomNavBar))
    }

    /**
     * Called (from Unity) when a new item is collected
     * @param contentId: The contentId of the content item collected
     * @return Int correlating to how the collection event was handled
     *  *Success Codes*
     *  0:   New content item was collected, and associated progress items were updated
     *  1:   New content item was collected, but not associated with a progress item
     *  2:   Content item was already collected
     *  *Error Codes*
     *  500: Experience was null in the activity, so progress could not be added to an associated experience
     *  501: Experience does not contain the given Content ID
     */
    @Suppress("unused")
    fun newCollectionEvent(contentId: String): Int {
        this.experienceModel?.id?.let { experienceId ->
            // If the contentId is not associated with the experience
            if( experienceModel?.content?.contentItems?.asSequence()?.map{ it.id }?.contains(contentId) != true ) {
                return 501
            }
            // If the content item was already collected
            if( experienceModel?.progress?.achievedContentItems?.contains(contentId) == true ) {
                return 2
            }
            // If the contentId is not found as an item associated with a progress
            if( experienceModel?.progress?.progressItems?.flatMap{ it.contentItems }?.contains(contentId) != true ) {
                return 1
            }
            // If uncollected new item
            progressViewModel.updateProgress(experienceId, contentId)
            return 0
        }
        return 500
    }

    /**
     * Called (from Unity) when the component has completed loading
     * @param eventCode Int correlating to the status of the loaded unity component
     * *Success Codes*
     * 0:   Unity component loaded successfully
     * TODO add more
     * *Error Codes*
     * 500: Unity component did not load successfully
     * TODO add more
     */
    @Suppress("unused")
    fun newLoadingCompletedEvent(eventCode: Int) {
        // TODO display unity
    }

    /**
     * Called (from Unity) when the component has crashed
     * @param eventCode
     */
    @Suppress("unused")
    fun newCrashEvent(eventCode: Int) {
        // TODO handle crash
    }

    /**
     * Called (from Unity) to direct the user to a specific content listing
     * @param contentId The contentId of the content item collected
     * @return Int correlating to how the view event was handled
     * *Success Codes*
     * 0:   The content was successfully viewed
     * TODO add more
     * *Error Codes*
     * 500: The content was not able to be viewed
     * TODO add more
     */
    @Suppress("unused")
    fun newViewContentEvent(contentId: String): Int {
        // TODO bring user to content view
        return 500
    }

    private fun switchToFragment(fragment: Fragment) {
        val transaction = supportFragmentManager?.beginTransaction() ?: return
        transaction.replace(R.id.experience_frame, fragment)
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when( item.itemId ) {
            android.R.id.home -> supportFragmentManager.popBackStack()
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