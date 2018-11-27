package com.comic_con.museum.ar.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.content.ContentFragment
import com.comic_con.museum.ar.experience.content.ContentItem

class ContentCardGridView(c: Context, a: AttributeSet): GridView(c, a) {

    private var parentFragment: ContentFragment? = null

    fun setUp(parentFragment: ContentFragment, contentItems: List<ContentItem>) {
        this.parentFragment = parentFragment
        val adapter = ContentAdapter(context)
        adapter.contentItems = contentItems
        this.adapter = adapter
    }

    inner class ContentAdapter(private val context: Context): BaseAdapter() {

        lateinit var contentItems: List<ContentItem>

        override fun getCount(): Int = contentItems.size

        override fun getItem(position: Int) = contentItems[position]

        override fun getItemId(position: Int) = getItem(position).contentId.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val categoryCardView: ContentCardView =
                convertView as? ContentCardView ?:
                LayoutInflater.from(context).inflate(R.layout.component_content_card_view, parent, false) as ContentCardView
            categoryCardView.setContent(this@ContentCardGridView.parentFragment, getItem(position))
            return categoryCardView
        }
    }
}