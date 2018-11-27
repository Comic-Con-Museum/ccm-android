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
import com.comic_con.museum.ar.experience.content.ContentFragment
import com.comic_con.museum.ar.experience.content.ContentItem
import java.io.IOException
import java.net.URL


class ContentCardView(c: Context, a: AttributeSet): LinearLayout(c, a) {

    private val thisImageLiveData: MutableLiveData<Bitmap> = MutableLiveData()

    init {
        thisImageLiveData.observeForever { bitmap ->
            this.findViewById<ImageView>(R.id.content_image).setImageBitmap(bitmap)
            this.requestLayout()
        }
    }

    fun setContent(parentFragment: ContentFragment?, content: ContentItem) {
        this.findViewById<TextView>(R.id.content_title)?.text = content.contentTitle

        AsyncTask.execute {
            try {
                val url = URL(content.contentImageURL)
                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                thisImageLiveData.postValue(bmp)
            } catch(e: IOException) { /* Some issue with the host */ }
        }

        this.findViewById<View>(R.id.content_image)?.setOnClickListener {
            parentFragment?.openContentView(content.contentId)
        }
    }
}