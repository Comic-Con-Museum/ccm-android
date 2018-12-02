package com.comic_con.museum.ar.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.content.activityfragments.ContentFragment
import com.comic_con.museum.ar.overview.ContentItem

class ContentSimpleListView(c: Context, a: AttributeSet): LinearLayout(c, a) {

    private var parentBaseFragment: ContentFragment? = null

    fun setUp(title: String, contentItems: List<ContentItem>) {
        this.parentBaseFragment = parentBaseFragment

        this.findViewById<TextView>(R.id.category_tag)?.text = title

        val holder = this.findViewById<ViewGroup>(R.id.content_holder) ?: return
        holder.removeAllViews()

        contentItems.forEach { thisItem ->
            val newCard = LayoutInflater.from(context)?.inflate(R.layout.component_content_overview_view, holder, false) as? ContentSimpleView ?: return@forEach
            newCard.setContent(thisItem)
            holder.addView(newCard)
        }
    }
}