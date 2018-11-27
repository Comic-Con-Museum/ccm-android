package com.comic_con.museum.ar.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.progress.Progress
import android.view.View
import android.widget.LinearLayout
import com.comic_con.museum.ar.experience.progress.ProgressModel


class ProgressView(c: Context, a: AttributeSet): LinearLayout(c, a) {

    fun setProgress(progressModel: ProgressModel, progress: Progress?) {
        this.findViewById<TextView>(R.id.progressTitle).text = progress?.progressName

        val relevantAchievedItems = progressModel.achievedContentItems.filter { achievedContentId ->
            achievedContentId in (progress?.contentItems ?: emptyList())
        }

        val progressFill = this.findViewById<ImageView>(R.id.progressBar)
        val parentWidth = (progressFill.parent as? View)?.layoutParams?.width ?: 0
        val fillPercent = (relevantAchievedItems.size.toFloat()/(progress?.contentItems?.size?.toFloat() ?: Float.MAX_VALUE))
        progressFill?.layoutParams?.width = (parentWidth * fillPercent).toInt()
    }

//    private fun dpToPx(dp: Float): Int {
//        return TypedValue.applyDimension(
//            TypedValue.COMPLEX_UNIT_DIP,
//            dp,
//            resources.displayMetrics
//        ).toInt()
//    }
}