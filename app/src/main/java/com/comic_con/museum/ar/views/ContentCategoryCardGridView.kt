package com.comic_con.museum.ar.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.content.Category
import com.comic_con.museum.ar.experience.content.ContentFragment

class ContentCategoryCardGridView(c: Context, a: AttributeSet): GridView(c, a) {

    private var contentFragment: ContentFragment? = null

    fun setUp(parentFragment: ContentFragment, categories: List<Category>) {
        val adapter = ContentCategoryAdapter(context)
        adapter.categories = categories
        this.adapter = adapter

        this.contentFragment = parentFragment
    }

    inner class ContentCategoryAdapter(private val context: Context): BaseAdapter() {

        lateinit var categories: List<Category>

        override fun getCount(): Int = categories.size

        override fun getItem(position: Int) = categories.get(position)

        override fun getItemId(position: Int) = getItem(position).categoryId.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val categoryCardView: ContentCategoryCardView =
                convertView as? ContentCategoryCardView ?:
                LayoutInflater.from(context).inflate(R.layout.component_category_card_view, parent, false) as ContentCategoryCardView
            categoryCardView.setCategory(this@ContentCategoryCardGridView.contentFragment, getItem(position))
            return categoryCardView
        }
    }
}