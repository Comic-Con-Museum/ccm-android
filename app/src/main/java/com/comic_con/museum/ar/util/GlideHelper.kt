package com.comic_con.museum.ar.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.comic_con.museum.ar.R

class GlideHelper {

    companion object {

        private val OPTIONS = RequestOptions()
            .placeholder(R.color.light_blue)
            .error(R.color.dark_blue)

        fun preloadImage(context: Context, url: String?) {
            Glide.with(context)
                .load(url)
                .preload()
        }

        fun loadImage(imageView: ImageView, url: String?) {
            Glide.with(imageView)
                .load(url)
                .apply(OPTIONS)
                .into(imageView)
        }
    }
}