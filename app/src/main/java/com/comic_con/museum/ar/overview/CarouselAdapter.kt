package com.comic_con.museum.ar.overview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.views.ExhibitCard

class CarouselAdapter(
    private val exhibitModels: List<ExhibitModel>
): RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    override fun getItemCount() = exhibitModels.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as? ExhibitCard)?.setup(exhibitModels.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val exhibitCard = inflater.inflate(R.layout.component_overview_card, parent, false)
        return ViewHolder(exhibitCard)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

}