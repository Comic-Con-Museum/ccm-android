package com.comic_con.museum.ar.experience.content

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.MenuItem
import com.comic_con.museum.ar.CCMApplication
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.ExperienceViewModel
import com.comic_con.museum.ar.experience.content.activityfragments.ContentFragment
import com.comic_con.museum.ar.experience.content.activityfragments.ContentListingFragment
import com.comic_con.museum.ar.experience.content.activityfragments.ContentSingleFragment
import java.lang.IllegalStateException
import javax.inject.Inject

class ContentActivity: AppCompatActivity() {

    @Inject
    lateinit var experienceViewModel: ExperienceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CCMApplication.getApplication().injectorComponent.inject(this)

        // Enable this to stop program until debugger attach
//        Debug.waitForDebugger()

        // Get the content item associated with this activity
        val contentItemId = intent?.extras?.getString(CONTENT_ITEM_ID_KEY) ?: throw IllegalStateException("Content Activity was started with no ContentItemId")
        val contentItemModel = experienceViewModel.getSpecificContent(
                    contentItemId) ?: throw IllegalStateException("Content Activity started with invalid ContentItemId: $contentItemId")


        // Set title
        this.title = contentItemModel.title

        // Show the initialized content
        setContentView(R.layout.activity_content)
        showContent(contentItemId, isInitial = true)
    }

    fun showContent(contentId: String, isInitial: Boolean = false) {
        val isCategory = experienceViewModel.isCategory(contentId)
        val targetFragment: ContentFragment = if( isCategory ) {
            ContentListingFragment()
        } else {
            ContentSingleFragment()
        }
        targetFragment.contentId = contentId
        val transaction = supportFragmentManager?.beginTransaction() ?: return
        transaction
            .replace(R.id.content_frame, targetFragment)
            .addToBackStack(
                if( isInitial ) {
                    INITIAL_FRAGMENT_TAG
                } else {
                    targetFragment.getContentTag()
                })
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when( item.itemId ) {
            android.R.id.home -> attemptPopOrFinish()
        }
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            attemptPopOrFinish()
            true
        } else super.onKeyDown(keyCode, event)
    }

    private fun attemptPopOrFinish() {
        val index = supportFragmentManager.backStackEntryCount - 1
        val backEntry = supportFragmentManager.getBackStackEntryAt(index)
        if( backEntry.name == INITIAL_FRAGMENT_TAG ) {
            this.finish()
        } else {
            this.supportFragmentManager.popBackStack()
        }
    }

    companion object {

        private const val INITIAL_FRAGMENT_TAG = "InitialFragment"
        private const val CONTENT_ITEM_ID_KEY = "ContentItemId"

        fun startContentActivity(context: Context, contentId: String) {
            val thisIntent = Intent(context, ContentActivity::class.java)
            thisIntent.putExtra(CONTENT_ITEM_ID_KEY, contentId)
            context.startActivity(thisIntent)
        }
    }
}