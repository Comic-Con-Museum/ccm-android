package com.comic_con.museum.ar.experience.content

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.comic_con.museum.ar.CCMApplication
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.ExperienceViewModel
import com.comic_con.museum.ar.overview.ExhibitModel
import com.comic_con.museum.ar.views.ContentCategoryGridView

class ContentFragment: Fragment() {

    // Not injected
    lateinit var experienceViewModel: ExperienceViewModel

    private var rootView: View? = null

    private var inflater: LayoutInflater? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        CCMApplication.getApplication().injectorComponent.inject(this)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_content, container, false)
        this.rootView = rootView
        this.inflater = inflater

        this.experienceViewModel.experienceModelLiveData.observeForever(this::setupContent)

        return rootView
    }

    /**
     * Sets up the content view
     */
    private fun setupContent(exhibitModel: ExhibitModel?) {
        exhibitModel ?: return
        val holder = this.rootView?.findViewById<ViewGroup>(R.id.category_list_container) ?: return
        // Remove all old category listings
        holder.removeAllViews()

        // Render all tag categories
        exhibitModel.category.allTags.forEach { categoryTag ->
            val theseCategories = exhibitModel.category.categories.filter { category ->
                categoryTag in category.categoryTags
            }
            renderNewTaggedCategoryGrid(holder, categoryTag, theseCategories)
        }

        // Render all non-tagged categories
        val untaggedCategories = exhibitModel.category.categories.filter { category ->
            category.categoryTags.count { tag ->
                tag !in exhibitModel.category.allTags
            } == 0
        }
        renderNewTaggedCategoryGrid(holder, null, untaggedCategories)
    }

    private fun renderNewTaggedCategoryGrid(holder: ViewGroup, tag: String?, categories: List<Category>) {
        val newCategoryListing = this.inflater?.inflate(R.layout.component_category_grid, holder, false) ?: return
        // Setup content
        newCategoryListing.findViewById<ContentCategoryGridView>(R.id.category_grid)?.setUp(categories)
        newCategoryListing.findViewById<TextView>(R.id.category_tag)?.text = tag ?: ""

        holder.addView(newCategoryListing)
    }
}