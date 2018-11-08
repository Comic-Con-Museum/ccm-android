package com.comic_con.museum.ar.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.comic_con.museum.ar.R

class ExhibitCard(c: Context, a: AttributeSet): LinearLayout(c, a) {

    var onContentSelected: () -> Unit = {}

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        this.findViewById<View>(R.id.main_image)?.setOnClickListener {
            onContentSelected()
        }
    }
}