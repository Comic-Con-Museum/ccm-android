package com.comic_con.museum.ar.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.ImageView
import android.R.attr.radius
import android.graphics.Path
import android.graphics.RectF
import android.util.TypedValue




class BottomRoundedImageView(c: Context, a: AttributeSet): ImageView(c,a) {

    private var clipPath: Path? = null

    override fun onDraw(canvas: Canvas?) {
        if( this.clipPath == null ) {
            this.clipPath = Path()
            val rect = RectF(0f, 0f, this.width.toFloat(), this.height.toFloat())
            this.clipPath?.addRoundRect(rect, dpToPx(6f), dpToPx(6f), Path.Direction.CW)
        }
        clipPath?.let {
            canvas?.clipPath(it)
        }
        super.onDraw(canvas)
    }

    private fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.displayMetrics
        )
    }
}