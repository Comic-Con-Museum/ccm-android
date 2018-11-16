package com.comic_con.museum.ar.experience.content

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comic_con.museum.ar.CCMApplication
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.views.ContentCategoryGridView
import javax.inject.Inject

class ContentFragment: Fragment() {

    @Inject
    lateinit var contentViewModel: ContentViewModel

    private var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CCMApplication.getApplication().injectorComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_content, container, false)
        this.rootView = rootView

        contentViewModel.categoryLiveData.observeForever(this::setUpCategoryView)

        return rootView
    }

    /**
     * Sets up the category view with the content model
     */
    private fun setUpCategoryView(categoryModel: CategoryModel?) {
        categoryModel ?: return
        rootView?.findViewById<ContentCategoryGridView>(R.id.category_grid)?.setUp(categoryModel)
    }
}