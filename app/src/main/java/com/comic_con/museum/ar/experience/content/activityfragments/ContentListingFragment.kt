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
import com.comic_con.museum.ar.views.ContentAdvancedListView
import javax.inject.Inject

class ContentListingFragment: ContentFragment(), Observer<ExperienceModel> {

    private var rootView: View? = null

    @Inject
    lateinit var experienceViewModel: ExperienceViewModel

    override fun getContentTag(): String {
        return "ContentListing"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CCMApplication.getApplication().injectorComponent.inject(this)

        activity?.actionBar?.hide()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val thisView = inflater.inflate(R.layout.fragment_content_listing, container, false)
        rootView = thisView
        return thisView
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

    override fun onPause() {
        super.onPause()
        experienceViewModel.experienceModelLiveData.removeObserver(this)
    }

    override fun onChanged(model: ExperienceModel?) {
        model ?: return

        val contentList = this.rootView?.findViewById<ContentAdvancedListView>(R.id.content_list) ?: return

        val thisCategoryItem = model.category.categories.find { categoryItem ->
            categoryItem.id == this.contentId
        } ?: return

        // Set title
        view?.findViewById<Toolbar>(R.id.new_toolbar)?.title = thisCategoryItem.title

        val thisSubContent = model.category.categories.union(model.content.contentItems).filter { contentItem ->
            contentItem.categories?.contains(this.contentId) ?: false
        }

        contentList.setUp(thisCategoryItem, thisSubContent)
    }
}