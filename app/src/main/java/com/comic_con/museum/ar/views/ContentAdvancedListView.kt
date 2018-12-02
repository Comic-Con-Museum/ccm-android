package com.comic_con.museum.ar.views

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.overview.ContentItem
import java.io.IOException
import java.net.URL

class ContentAdvancedListView(c: Context, a: AttributeSet): LinearLayout(c, a) {

    private val thisImageLiveData: MutableLiveData<Bitmap> = MutableLiveData()

    init {
        thisImageLiveData.observeForever { bitmap ->
            this.findViewById<ImageView>(R.id.content_image)?.setImageBitmap(bitmap)
            this.requestLayout()
        }
    }

    fun setUp(subjectContent: ContentItem, contentItems: List<ContentItem>) {

        AsyncTask.execute {
            try {
                val url = URL(subjectContent.imageUrl)
                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                thisImageLiveData.postValue(bmp)
            } catch(e: IOException) { /* Some issue with the host */ }
        }

        this.findViewById<TextView>(R.id.content_description)?.text = subjectContent.description

        val holder = this.findViewById<ViewGroup>(R.id.content_holder) ?: return
        holder.removeAllViews()

        contentItems.forEach { thisItem ->
            val newCard = LayoutInflater.from(context)?.inflate(R.layout.component_content_advanced_view, holder, false) as? ContentAdvancedView ?: return@forEach
            newCard.setContent(thisItem)
            holder.addView(newCard)
        }
    }
}