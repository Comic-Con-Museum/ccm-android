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
import android.widget.ScrollView
import android.widget.TextView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.overview.ContentItem
import java.io.IOException
import java.net.URL

class ContentView(c: Context, a: AttributeSet): ScrollView(c, a) {

    private val thisImageLiveData = MutableLiveData<Bitmap>()

    init {
        thisImageLiveData.observeForever {
            this.findViewById<ImageView>(R.id.content_image)?.setImageBitmap(it)
        }
    }

    fun setUpContent(contentItem: ContentItem) {
        // Set up non-dynamic content
        this.findViewById<TextView>(R.id.content_title)?.text = contentItem.title
        this.findViewById<TextView>(R.id.content_description)?.text = contentItem.description
        AsyncTask.execute {
            try {
                val url = URL(contentItem.imageUrl)
                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                thisImageLiveData.postValue(bmp)
            } catch(e: IOException) { /* Some issue with the host */ }
        }

        // Set up key/value dynamics
        this.findViewById<ViewGroup>(R.id.key_value_holder)?.let { holder ->
            holder.removeAllViews()
            contentItem.extraPairs?.forEach { contentPair ->
                val newKeyValue = LayoutInflater.from(context).inflate(R.layout.component_content_key_value_listing, holder, false)
                newKeyValue?.findViewById<TextView>(R.id.key)?.text = contentPair.label
                newKeyValue?.findViewById<TextView>(R.id.value)?.text = contentPair.value
                holder.addView(newKeyValue)
            }
        }

    }
}