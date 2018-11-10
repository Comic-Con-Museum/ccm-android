package com.comic_con.museum.ar.views

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.overview.ExhibitModel
import android.util.DisplayMetrics
import com.comic_con.museum.ar.experience.ExperienceActivity


class ExhibitCard(c: Context, a: AttributeSet): LinearLayout(c, a) {

    // The percent of the screen this view should take up
    @Suppress("PrivatePropertyName")
    private val PERCENT_SCREEN_WIDTH = 80f

    fun setup(model: ExhibitModel) {
        this.findViewById<TextView>(R.id.title_text)?.text = model.exhibitTitle
        this.findViewById<TextView>(R.id.body_text)?.text = model.exhibitDescription
        this.layoutParams.width = getWidthPx()

        // Set onClick to move to exhibit content
        this.findViewById<View>(R.id.main_image)?.setOnClickListener {
            // Start the new Experience Activity and pass in the id of the Experience
            val thisExperienceBundle = Bundle()
            thisExperienceBundle.putString(EXPERIENCE_ID_KEY, model.exhibitId)
            (context as? AppCompatActivity)?.startActivity(ExperienceActivity.createIntent(context), thisExperienceBundle)
        }
    }

    /**
     * Gets the width of this view in pixels. This is made from calculating the Screen's width and multiplying
     * it with the PERCENT_SCREEN_WIDTH
     */
    private fun getWidthPx(): Int {
        val displayMetrics = DisplayMetrics()
        (context as? AppCompatActivity)?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        return (displayMetrics.widthPixels * (PERCENT_SCREEN_WIDTH/100f)).toInt()
    }

    companion object {
        const val EXPERIENCE_ID_KEY = "experience_id"
    }
}