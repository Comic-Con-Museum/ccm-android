package com.comic_con.museum.ar.views

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.overview.ExhibitModel
import android.util.DisplayMetrics
import android.widget.ImageView
import com.comic_con.museum.ar.MainActivity
import net.cachapa.expandablelayout.ExpandableLayout


class ExhibitCard(c: Context, a: AttributeSet): LinearLayout(c, a) {

    private var isInitialMeasure = true

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
            (context as? MainActivity)?.beginExperienceActivity(model.exhibitId)
        }

        this.findViewById<ImageView>(R.id.more_info)?.let { moreInfoView ->
            moreInfoView.setOnClickListener {
                this.findViewById<ExpandableLayout>(R.id.expandable_content)?.toggle(true)
            }
        }
    }

    fun setupMoreContent() {
        this.layoutParams.width = getWidthPx()
        this.findViewById<View>(R.id.body_text)?.visibility = View.GONE
        this.findViewById<View>(R.id.icons)?.visibility = View.GONE
        this.findViewById<TextView>(R.id.title_text)?.text = context.getText(R.string.overview_see_more)
        this.findViewById<TextView>(R.id.title_text)?.textSize = 24F
        this.findViewById<TextView>(R.id.title_text)?.textAlignment = View.TEXT_ALIGNMENT_CENTER
        this.findViewById<ImageView>(R.id.main_image)?.setImageDrawable(context.getDrawable(R.drawable.plus))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // We want it to measure the view as having been expanded, so we keep it expanded
        // until after the first measure
        if( isInitialMeasure ) {
            this.findViewById<ExpandableLayout>(R.id.expandable_content)?.toggle(false)
            isInitialMeasure = false
            invalidate()
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