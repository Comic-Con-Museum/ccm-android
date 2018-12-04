package com.comic_con.museum.ar.experience.content.activityfragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comic_con.museum.ar.CCMApplication
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.ExperienceViewModel
import com.comic_con.museum.ar.overview.ExperienceModel
import com.comic_con.museum.ar.overview.Priority
import com.comic_con.museum.ar.views.ContentAdvancedListView
import com.comic_con.museum.ar.views.ContentAdvancedSingleView
import javax.inject.Inject

class ContentSingleFragment: ContentFragment(), Observer<ExperienceModel> {

    var rootView: View? = null

    @Inject
    lateinit var experienceViewModel: ExperienceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CCMApplication.getApplication().injectorComponent.inject(this)

        activity?.actionBar?.hide()
    }

    override fun getContentTag(): String {
        return "ContentSingle"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val thisView = inflater.inflate(R.layout.fragment_content_single, container, false) ?: return null
        this.rootView = thisView
        return thisView
    }

    override fun onPause() {
        super.onPause()
        experienceViewModel.experienceModelLiveData.removeObserver(this)
    }

    override fun onResume() {
        super.onResume()
        this.activity?.let {
            experienceViewModel.experienceModelLiveData.observe(it, this)
        }

        // Set up the action bar for this fragment and activity
        (activity as? AppCompatActivity)?.let { thisActivity ->
            thisActivity.supportActionBar?.hide()
            val newToolbar = view?.findViewById<Toolbar>(R.id.new_toolbar)
            thisActivity.setSupportActionBar(newToolbar)
            thisActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onChanged(model: ExperienceModel?) {
        model ?: return

        val contentList = this.rootView?.findViewById<ContentAdvancedSingleView>(R.id.content_single) ?: return

        val thisContentItem = model.content.contentItems.find { contentItem ->
            contentItem.id == this.contentId
        } ?: return

        // Set title
        view?.findViewById<Toolbar>(R.id.new_toolbar)?.title = thisContentItem.title

        val associatedContentItemsIds = thisContentItem.extraPairs?.map { pair ->
            pair.contentReferenceId
        }

        val primaryContentItems = model.category.categories.union(model.content.contentItems).filter { contentItem ->
            (associatedContentItemsIds?.contains(contentItem.id) ?: false) &&
                    thisContentItem.extraPairs?.find { it.contentReferenceId == contentItem.id }?.priority == Priority.PRIMARY
        }

        val secondaryContentItems = model.category.categories.union(model.content.contentItems).filter { contentItem ->
            (associatedContentItemsIds?.contains(contentItem.id) ?: false) &&
                    thisContentItem.extraPairs?.find { it.contentReferenceId == contentItem.id }?.priority == Priority.SECONDARY
        }

        contentList.setUp(thisContentItem, primaryContentItems, secondaryContentItems)
    }
}