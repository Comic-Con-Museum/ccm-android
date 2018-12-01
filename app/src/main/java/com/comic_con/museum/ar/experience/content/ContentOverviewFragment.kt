package com.comic_con.museum.ar.experience.content

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comic_con.museum.ar.CCMApplication
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.ExperienceViewModel
import com.comic_con.museum.ar.experience.content.activityfragments.ContentFragment
import com.comic_con.museum.ar.overview.ExhibitModel
import com.comic_con.museum.ar.views.ContentSimpleListView
import javax.inject.Inject

class ContentOverviewFragment: ContentFragment(), Observer<ExhibitModel?> {

    private var rootView: View? = null

    @Inject
    lateinit var experienceViewModel: ExperienceViewModel

    override fun getContentTag(): String {
        return "ContentOverview"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CCMApplication.getApplication().injectorComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val thisView = inflater.inflate(R.layout.fragment_content_overview, container, false)
        this.rootView = thisView
        return thisView
    }

    override fun onResume() {
        super.onResume()
        this.activity?.let {
            experienceViewModel.experienceModelLiveData.observe(it, this)
        }
    }

    override fun onPause() {
        super.onPause()
        experienceViewModel.experienceModelLiveData.removeObserver(this)
    }

    override fun onChanged(model: ExhibitModel?) {
        model ?: return
        val contentRoot = this.rootView?.findViewById<ViewGroup>(R.id.content_overview_container) ?: return

        contentRoot.removeAllViews()
        model.contentTags.forEach { thisTag ->
            val contentList = this.layoutInflater?.inflate(R.layout.component_content_overview_list, contentRoot, false) as? ContentSimpleListView ?: return@forEach
            contentList.setUp( thisTag,
                model.category.categories.union(model.content.contentItems).filter { thisCategory ->
                    thisCategory.tags.contains(thisTag)
                }
            )

            contentRoot.addView(contentList)
        }
    }
}