package com.comic_con.museum.ar.views

import android.content.Context
import android.os.Handler
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

    private var onClickEnabled = true

    fun setContent(content: ContentItem) {
        this.findViewById<TextView>(R.id.content_title)?.text = content.title
        this.findViewById<TextView>(R.id.content_subtitle)?.text = getSubtitle(content)

        this.findViewById<ImageView>(R.id.content_image)?.let { imageView ->
            GlideHelper.loadImage(imageView, content.imageUrl)
        }

        this.findViewById<View>(R.id.content_image)?.setOnClickListener {
            if( !onClickEnabled ) return@setOnClickListener
            // prevent multiple onClicks
            onClickEnabled = false
            Handler().postDelayed({
                onClickEnabled = true
            }, 1000)
            // Start an activity with the related content
            this.context?.let { thisContext ->
                ContentActivity.startContentActivity(thisContext, content.id)
            }
        }
    }

    private fun getSubtitle(content: ContentItem): String {
        return content.extraPairs?.map { it.value }?.joinToString(", ") ?: ""
    }
}