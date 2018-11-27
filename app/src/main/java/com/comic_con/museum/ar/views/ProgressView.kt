package com.comic_con.museum.ar.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.progress.Progress
import android.view.View
import android.widget.LinearLayout
import com.comic_con.museum.ar.experience.progress.ProgressModel


class ProgressView(c: Context, a: AttributeSet): LinearLayout(c, a) {

    fun setProgress(progressModel: ProgressModel, progress: Progress?) {
        progress ?: return
        // Get the achieved items for this progress item
        val relevantAchievedItems = progressModel.achievedContentItems.filter { achievedContentId ->
            achievedContentId in (progress.contentItems)
        }

        // Set text values
        this.findViewById<TextView>(R.id.progressTitle)?.text = progress.progressName
        this.findViewById<TextView>(R.id.achievedProgressNum)?.text = relevantAchievedItems.size.toString()
        this.findViewById<TextView>(R.id.maxProgressNum)?.text = progress.contentItems.size.toString()

        // Set progress bar
        this.findViewById<LinearLayout>(R.id.progressBarContainer)?.weightSum = progress.contentItems.size.toFloat()
        (this.findViewById<View>(R.id.progressBar)?.layoutParams as? LinearLayout.LayoutParams)?.weight = relevantAchievedItems.size.toFloat()
    }
}