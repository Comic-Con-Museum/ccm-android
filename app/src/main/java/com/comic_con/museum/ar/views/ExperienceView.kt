package com.comic_con.museum.ar.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.comic_con.museum.ar.MainActivity
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.overview.ExperienceModel
import com.comic_con.museum.ar.util.GlideHelper

class ExperienceView(c: Context, a: AttributeSet): LinearLayout(c,a) {

    fun setup(experience: ExperienceModel) {
        this.findViewById<TextView>(R.id.experience_title)?.text = experience.title
        this.findViewById<TextView>(R.id.experience_description)?.text = experience.description
        this.clipToOutline = true

        val imageView = this.findViewById<ImageView>(R.id.experience_image) ?: return

        // Add onclick to start experience activity
        imageView.setOnClickListener {
            (this.context as? MainActivity)?.beginExperienceActivity(experience.id)
        }

        GlideHelper.loadImage(imageView, experience.imageUrl)
    }

}