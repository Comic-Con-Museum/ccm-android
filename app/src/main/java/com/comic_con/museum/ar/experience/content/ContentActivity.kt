package com.comic_con.museum.ar.experience.content

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.support.v7.app.AppCompatActivity
import com.comic_con.museum.ar.CCMApplication
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.ExperienceViewModel
import com.comic_con.museum.ar.experience.content.activityfragments.ContentFragment
import com.comic_con.museum.ar.experience.content.activityfragments.ContentListingFragment
import com.comic_con.museum.ar.experience.content.activityfragments.ContentSingleFragment
import com.comic_con.museum.ar.overview.ExhibitModel
import java.lang.IllegalStateException
import javax.inject.Inject

class ContentActivity: AppCompatActivity() {

    @Inject
    lateinit var experienceViewModel: ExperienceViewModel

    lateinit var experienceModel: ExhibitModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CCMApplication.getApplication().injectorComponent.inject(this)

        // Enable this to stop program until debugger attach
//        Debug.waitForDebugger()

        // Get the content item associated with this activity
        val contentItemId = intent?.extras?.getString(CONTENT_ITEM_ID_KEY) ?: throw IllegalStateException("Content Activity was started with no ContentItemId")
        this.experienceModel = experienceViewModel.experienceModelLiveData.value ?: return
        val contentItemModel = experienceViewModel.getSpecificContent(
            experienceModel, contentItemId
        ) ?: throw IllegalStateException("Content Activity started with invalid ContentItemId: $contentItemId")


        // Set title
        this.title = contentItemModel.title

        // Show the initialized content
        setContentView(R.layout.activity_content)
        showContent(contentItemId)
    }

    fun showContent(contentId: String) {
        val isCategory = experienceViewModel.isCategory(experienceModel, contentId)
        val targetFragment: ContentFragment = if( isCategory ) {
            ContentListingFragment()
        } else {
            ContentSingleFragment()
        }
        targetFragment.contentId = contentId
        val transaction = supportFragmentManager?.beginTransaction() ?: return
        transaction
            .replace(R.id.content_frame, targetFragment)
            .addToBackStack(targetFragment.getContentTag())
            .commit()
    }

    companion object {
        private const val CONTENT_ITEM_ID_KEY = "ContentItemId"

        fun startContentActivity(context: Context, contentId: String) {
            val thisIntent = Intent(context, ContentActivity::class.java)
            thisIntent.putExtra(CONTENT_ITEM_ID_KEY, contentId)
            context.startActivity(thisIntent)
        }
    }
}