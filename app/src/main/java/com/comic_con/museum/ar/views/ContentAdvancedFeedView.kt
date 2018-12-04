package com.comic_con.museum.ar.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.overview.ContentItem

class ContentAdvancedFeedView(c: Context, a: AttributeSet): LinearLayout(c,a) {

    fun setup(contentItems: List<ContentItem>) {
        this.removeAllViews()

        contentItems.forEach { thisItem ->
            val newCard = LayoutInflater.from(context)?.inflate(R.layout.component_content_advanced_view, this, false) as? ContentAdvancedView ?: return@forEach
            newCard.setContent(thisItem)
            this.addView(newCard)
        }
    }
}