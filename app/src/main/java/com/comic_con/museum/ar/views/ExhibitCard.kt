package com.comic_con.museum.ar.views

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
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
import java.io.IOException
import java.net.URL


class ExhibitCard(c: Context, a: AttributeSet): LinearLayout(c, a) {

    private var isInitialMeasure = true

    private val exhibitImageLiveData = MutableLiveData<Bitmap>()

    private var clicked = false

    // The percent of the screen this view should take up
    @Suppress("PrivatePropertyName")
    private val PERCENT_SCREEN_WIDTH = 80f

    fun setup(model: ExhibitModel) {
        this.findViewById<TextView>(R.id.title_text)?.text = model.exhibitTitle
        this.findViewById<TextView>(R.id.body_text)?.text = model.exhibitDescription
        this.layoutParams.width = getWidthPx()

        AsyncTask.execute {
            try {
                val url = URL(model.exhibitImageUrl)
                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                exhibitImageLiveData.postValue(bmp)
            } catch(e: IOException) { /* Some issue with the host */ }
        }
        exhibitImageLiveData.observeForever {
            this.findViewById<ImageView>(R.id.main_image)?.setImageBitmap(it)
            requestLayout()
        }

        // Set onClick to move to exhibit content
        this.findViewById<View>(R.id.main_image)?.setOnClickListener {
            synchronized(this.clicked) {
                if (!this.clicked) {
                    this.clicked = true
                    // Start the new Experience Activity and pass in the id of the Experience
                    (context as? MainActivity)?.beginExperienceActivity(model.exhibitId)
                }
            }
        }

        this.findViewById<ImageView>(R.id.more_info)?.let { moreInfoView ->
            moreInfoView.setOnClickListener {
                this.findViewById<ExpandableLayout>(R.id.expandable_content)?.toggle(true)
            }
        }

        this.findViewById<TextView>(R.id.extra_content)?.text = model.exhibitAdditionDescription
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
}