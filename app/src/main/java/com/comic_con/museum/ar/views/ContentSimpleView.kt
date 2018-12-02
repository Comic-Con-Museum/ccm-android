package com.comic_con.museum.ar.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.content.ContentActivity
import com.comic_con.museum.ar.overview.ContentItem
import com.comic_con.museum.ar.util.GlideHelper


class ContentSimpleView(c: Context, a: AttributeSet): LinearLayout(c, a) {

    fun setContent(content: ContentItem) {
        this.findViewById<TextView>(R.id.content_title)?.text = content.title
        this.findViewById<TextView>(R.id.content_subtitle)?.text = getSubtitle(content)

        this.findViewById<ImageView>(R.id.content_image)?.let { imageView ->
            GlideHelper.loadImage(imageView, content.imageUrl)
        }

        this.findViewById<View>(R.id.content_image)?.setOnClickListener {
            this.context?.let { thisContext ->
                ContentActivity.startContentActivity(thisContext, content.id)
            }
        }
    }

    private fun getSubtitle(content: ContentItem): String {
        return content.extraPairs?.map { it.value }?.joinToString(", ") ?: ""
    }
}