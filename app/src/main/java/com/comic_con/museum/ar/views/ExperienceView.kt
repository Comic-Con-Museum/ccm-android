package com.comic_con.museum.ar.views

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.AsyncTask
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.comic_con.museum.ar.MainActivity
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.overview.ExhibitModel
import java.io.IOException
import java.net.URL

class ExperienceView(c: Context, a: AttributeSet): LinearLayout(c,a) {

    private val thisImageLiveData: MutableLiveData<Bitmap> = MutableLiveData()

    init {
        thisImageLiveData.observeForever { bitmap ->
            this.findViewById<ImageView>(R.id.experience_image)?.setImageBitmap(bitmap)
            this.requestLayout()
        }
    }

    fun setup(experience: ExhibitModel) {
        this.findViewById<TextView>(R.id.experience_title)?.text = experience.title
        this.findViewById<TextView>(R.id.experience_description)?.text = experience.description
        this.clipToOutline = true

        // Add onclick to start experience activity
        this.findViewById<ImageView>(R.id.experience_image)?.setOnClickListener {
            (this.context as? MainActivity)?.beginExperienceActivity(experience.id)
        }

        AsyncTask.execute {
            try {
                val url = URL(experience.imageUrl)
                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                thisImageLiveData.postValue(bmp)
            } catch(e: IOException) { /* Some issue with the host */ }
        }
    }

}