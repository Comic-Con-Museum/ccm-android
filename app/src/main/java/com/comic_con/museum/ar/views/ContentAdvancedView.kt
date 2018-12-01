package com.comic_con.museum.ar.views

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.comic_con.museum.ar.R
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.View
import android.widget.ImageView
import com.comic_con.museum.ar.experience.content.ContentActivity
import com.comic_con.museum.ar.experience.content.activityfragments.ContentFragment
import com.comic_con.museum.ar.overview.ContentItem
import java.io.IOException
import java.net.URL


class ContentAdvancedView(c: Context, a: AttributeSet): LinearLayout(c, a) {

    private val thisImageLiveData: MutableLiveData<Bitmap> = MutableLiveData()

    init {
        thisImageLiveData.observeForever { bitmap ->
            this.findViewById<ImageView>(R.id.content_image)?.setImageBitmap(bitmap)
            this.requestLayout()
        }
    }

    fun setContent(content: ContentItem) {
        this.findViewById<TextView>(R.id.content_title)?.text = content.title
        this.findViewById<TextView>(R.id.content_subtitle)?.text = getSubtitle(content)

        AsyncTask.execute {
            try {
                val url = URL(content.imageUrl)
                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                thisImageLiveData.postValue(bmp)
            } catch(e: IOException) { /* Some issue with the host */ }
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