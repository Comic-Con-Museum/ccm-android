package com.comic_con.museum.ar.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.progress.Progress
import android.util.TypedValue


class ProgressView(c: Context, a: AttributeSet): RelativeLayout(c, a) {

    fun setProgress(progress: Progress?) {
        this.findViewById<TextView>(R.id.progress_text).text = progress?.progressName

        val progressFill = this.findViewById<ImageView>(R.id.progress_fill)
        progressFill?.layoutParams?.height = dpToPx(
            100 * ((progress?.achievedContentItems?.size ?: 0).toFloat()/(progress?.contentItems?.size ?: Int.MAX_VALUE).toFloat())
        )
    }

    private fun dpToPx(dp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.displayMetrics
        ).toInt()
    }
}