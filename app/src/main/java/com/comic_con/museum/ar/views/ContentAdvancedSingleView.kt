package com.comic_con.museum.ar.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.overview.ContentItem
import com.comic_con.museum.ar.util.GlideHelper

class ContentAdvancedSingleView(c: Context, a: AttributeSet): LinearLayout(c, a) {

    fun setUp(subjectContent: ContentItem, primaryContentItems: List<ContentItem>, secondaryContentItems: List<ContentItem>) {

        this.findViewById<ImageView>(R.id.content_image)?.let { imageView ->
            GlideHelper.loadImage(imageView, subjectContent.imageUrl)
        }

        this.findViewById<TextView>(R.id.content_description)?.text = subjectContent.description

        this.findViewById<ContentAdvancedFeedView>(R.id.content_holder_primary)?.setup(primaryContentItems)

        this.findViewById<ContentAdvancedFeedView>(R.id.content_holder_secondary)?.setup(secondaryContentItems)
    }
}