package com.comic_con.museum.ar.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.GridView
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.content.ContentModel

class ContentCategoryGridView(c: Context, a: AttributeSet): GridView(c, a) {

    fun setUp(contentModel: ContentModel) {
        val adapter = ContentCategoryAdapter(context)
        adapter.contentModel = contentModel
        this.adapter = adapter

        this.onItemClickListener = adapter.getItemClickListener()
    }

    inner class ContentCategoryAdapter(private val context: Context): BaseAdapter() {

        lateinit var contentModel: ContentModel

        override fun getCount(): Int = contentModel.categories.size

        override fun getItem(position: Int) = contentModel.categories.get(position)

        override fun getItemId(position: Int) = getItem(position).categoryId.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            (convertView as? ContentCategoryView)?.let {
                return it
            }
            val newCategoryView = LayoutInflater.from(context).inflate(R.layout.component_category_view, parent, false) as ContentCategoryView
            newCategoryView.setCategory(getItem(position))
            return newCategoryView
        }

        fun getItemClickListener(): AdapterView.OnItemClickListener {
            return AdapterView.OnItemClickListener { parent, view, position, id ->
                // TODO
            }
        }
    }
}