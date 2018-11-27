package com.comic_con.museum.ar.views

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.content.Category
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import com.comic_con.museum.ar.experience.content.ContentFragment
import java.net.URL


class ContentCategoryCardView(c: Context, a: AttributeSet): LinearLayout(c, a) {

    private val thisImageLiveData: MutableLiveData<Bitmap> = MutableLiveData()

    init {
        thisImageLiveData.observeForever { bitmap ->
            this.findViewById<ImageView>(R.id.category_image).setImageBitmap(bitmap)
            this.requestLayout()
        }
    }

    fun setCategory(parentFragment: ContentFragment?, category: Category) {
        this.findViewById<TextView>(R.id.title_text)?.text = category.categoryTitle

        AsyncTask.execute {
            val url = URL(category.categoryImage)
            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            thisImageLiveData.postValue(bmp)
        }

        this.findViewById<ImageView>(R.id.category_image)?.setOnClickListener {
            parentFragment?.openContentListingView(category.categoryId)
        }
    }
}