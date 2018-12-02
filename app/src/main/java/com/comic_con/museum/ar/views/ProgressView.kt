package com.comic_con.museum.ar.views

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.TextView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.progress.Progress
import android.view.View
import android.widget.LinearLayout
import com.comic_con.museum.ar.CCMApplication
import com.comic_con.museum.ar.experience.ExperienceViewModel
import com.comic_con.museum.ar.experience.progress.ProgressModel
import java.util.zip.Inflater
import javax.inject.Inject


class ProgressView(c: Context, a: AttributeSet): LinearLayout(c, a) {

    @Inject
    lateinit var experienceViewModel: ExperienceViewModel

    private val titleText by lazy {
        this.findViewById<TextView>(R.id.progressTitle)
    }
    private val achievedProgressText by lazy {
        this.findViewById<TextView>(R.id.achievedProgressNum)
    }
    private val progressBarContainer by lazy {
        this.findViewById<LinearLayout>(R.id.progressBarContainer)
    }

    init {
        CCMApplication.getApplication().injectorComponent.inject(this)
    }

    fun setProgress(progressModel: ProgressModel, progress: Progress?) {
        progress ?: return
        // Get the achieved items for this progress item
        val relevantAchievedItems = progressModel.achievedContentItems.filter { achievedContentId ->
            achievedContentId in (progress.contentItems)
        }

        // Set text values
        titleText?.text = progress.progressName

        // Set progress item contents
//        this.findViewById<LinearLayout>(R.id.progress_item_holder)?.let { progressItemHolder ->
//            // Clean up old values
//            progressItemHolder.removeAllViews()
//            // Populate with new values
//            relevantAchievedItems.map { contentId ->
//                experienceViewModel.getSpecificContent(contentId) ?: return@map
//            }.forEach { collectedItem ->
//                val thisContentView = LayoutInflater.from(this.context)?.inflate(
//                    R.layout.component_progress_collected_item, progressItemHolder, false) ?: return@forEach
//                // TODO populate view
////                progressItemHolder.addView(thisContentView)
//            }
//        }

        // Set dropdown listener
//        this.findViewById<View>(R.id.more_info_toggle)?.let { dropDownToggle ->
//            dropDownToggle.setOnClickListener {
//                this.findViewById<ExpandableLayout>(R.id.expandable_content)?.let { expandableLayout ->
//                    expandableLayout.toggle()
//                }
//            }
//        }

        // If progress complete
        if (relevantAchievedItems.size >= progress.contentItems.size) {
            // Update background
            this.background = ContextCompat.getDrawable(this.context, R.drawable.progress_background_complete)

            // Update progress bar
            progressBarContainer.background = ContextCompat.getDrawable(this.context, R.drawable.progress_bar_complete)

            // Update text color
            titleText.setTextColor(ContextCompat.getColor(this.context, R.color.black))

            // Hide/update progress numbers
            achievedProgressText?.text = this.context.getString(R.string.progress_completed)
            achievedProgressText?.setTextColor(ContextCompat.getColor(this.context, R.color.black))
        }
        // Else progress is not complete
        else {
            val progressNumText = "${relevantAchievedItems.size}/${progress.contentItems.size}"
            achievedProgressText?.text = progressNumText

            // Set progress bar
            progressBarContainer?.weightSum = progress.contentItems.size.toFloat()
            (this.findViewById<View>(R.id.progressBar)?.layoutParams as? LinearLayout.LayoutParams)?.weight =
                    relevantAchievedItems.size.toFloat()

        }
    }
}