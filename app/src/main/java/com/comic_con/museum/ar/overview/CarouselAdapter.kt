package com.comic_con.museum.ar.overview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.views.ExperienceCard

class CarouselAdapter(
    private val experienceModels: List<ExperienceModel>
): RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    override fun getItemCount() = experienceModels.size + 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if( position == 0 ) {
            (holder.itemView as? ExperienceCard)?.setupMoreContent()
        } else {
            (holder.itemView as? ExperienceCard)?.setup(experienceModels.get(position - 1))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val experienceCard = inflater.inflate(R.layout.component_overview_card, parent, false)
        return ViewHolder(experienceCard)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}