package com.comic_con.museum.ar.views

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class SwipeDisabledViewPager(c: Context, a: AttributeSet): ViewPager(c, a) {

    init {
        // Prevent fragments off screen from being killed
        this.offscreenPageLimit = 3
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}