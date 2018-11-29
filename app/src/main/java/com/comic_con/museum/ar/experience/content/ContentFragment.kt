package com.comic_con.museum.ar.experience.content

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.ExperienceViewModel
import com.comic_con.museum.ar.overview.ExperienceModel
import com.comic_con.museum.ar.views.ContentCardGridView
import com.comic_con.museum.ar.views.ContentCategoryCardGridView
import com.comic_con.museum.ar.views.ContentView

class ContentFragment: Fragment() {

    // Not injected
    lateinit var experienceViewModel: ExperienceViewModel

    private var rootView: View? = null

    private var inflater: LayoutInflater? = null

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
    private fun setupContent(experienceModel: ExperienceModel?) {
        experienceModel ?: return
        val holder = getContentHolder() ?: return
        // Remove all old category listings
        holder.removeAllViews()

        openCategoryListingView(holder, null, experienceModel.category.categories)

        // TODO issue #42
        // Render all tag categories
//        experienceModel.category.allTags.forEach { categoryTag ->
//            val theseCategories = experienceModel.category.categories.filter { category ->
//                categoryTag in category.categoryTags
//            }
//            openCategoryListingView(holder, categoryTag, theseCategories)
//        }
//
//        // Render all non-tagged categories
//        val untaggedCategories = experienceModel.category.categories.filter { category ->
//            category.categoryTags.count { tag ->
//                tag !in experienceModel.category.allTags
//            } == 0
//        }
//        openCategoryListingView(holder, null, untaggedCategories)
    }

    private fun openCategoryListingView(holder: ViewGroup, tag: String?, categories: List<Category>) {
        val newCategoryListing = this.inflater?.inflate(R.layout.component_category_grid, holder, false) ?: return
        // Setup content
        newCategoryListing.findViewById<ContentCategoryCardGridView>(R.id.category_grid)?.setUp(this, categories)
//        newCategoryListing.findViewById<TextView>(R.id.category_tag)?.text = tag ?: ""

        holder.addView(newCategoryListing)
    }

    fun openContentListingView(categoryId: String) {
        val holder = getContentHolder() ?: return
        val model = this.experienceViewModel.experienceModelLiveData.value ?: return

        holder.removeAllViews()

        val newContentListing = this.inflater?.inflate(R.layout.component_content_grid, holder, false) ?: return

        val categorizedContent = model.content.contentItems.filter { contentItem ->
            contentItem.contentCategories.contains(categoryId)
        }

        newContentListing.findViewById<ContentCardGridView>(R.id.content_grid)?.setUp(this, categorizedContent)

        holder.addView(newContentListing)
    }

    fun openContentView(contentId: String) {
        val holder = getContentHolder() ?: return
        val model = this.experienceViewModel.experienceModelLiveData.value ?: return
        val contentModel = model.content.contentItems.find { it.contentId == contentId } ?: return

        holder.removeAllViews()

        val newContentView = this.inflater?.inflate(R.layout.component_content_view, holder, false) as? ContentView ?: return
        newContentView.setUpContent(contentModel)

        holder.addView(newContentView)
    }

    private fun getContentHolder() = this.rootView?.findViewById<ViewGroup>(R.id.content_holder)
}