package com.comic_con.museum.ar.experience.content.activityfragments

import android.support.v4.app.Fragment

abstract class ContentFragment: Fragment() {

    // This should be set before the fragment is rendered
    lateinit var contentId: String

    abstract fun getContentTag(): String

}