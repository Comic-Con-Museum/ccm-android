package com.comic_con.museum.ar.views

import android.content.res.Resources
import android.util.TypedValue

class ViewUtil {
    companion object {

        fun dpToPx(resources: Resources, value: Float): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value,
                resources.displayMetrics
            ).toInt()
        }
    }
}