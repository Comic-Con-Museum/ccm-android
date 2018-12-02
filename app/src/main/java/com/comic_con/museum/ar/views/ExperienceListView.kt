package com.comic_con.museum.ar.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.overview.ExperienceModel

class ExperienceListView(c: Context, a: AttributeSet): ListView(c,a) {

    fun setUp(experienceList: List<ExperienceModel>) {
        val adapter = ProgressAdapter(context)
        adapter.experiences = experienceList
        this.adapter = adapter
    }

    inner class ProgressAdapter(private val context: Context): BaseAdapter() {

        lateinit var experiences: List<ExperienceModel>

        override fun getCount(): Int = experiences.size

        override fun getItem(position: Int) = experiences[position]

        override fun getItemId(position: Int) = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val experienceView: ExperienceView =
                convertView as? ExperienceView ?:
                LayoutInflater.from(context).inflate(R.layout.component_experience_view, parent, false) as ExperienceView
            experienceView.setup(getItem(position))
            return experienceView
        }
    }
}