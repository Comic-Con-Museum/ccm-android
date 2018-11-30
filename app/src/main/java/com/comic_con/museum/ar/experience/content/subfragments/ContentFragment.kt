package com.comic_con.museum.ar.experience.content.subfragments

import android.support.v4.app.Fragment

abstract class ContentFragment: Fragment() {

    abstract fun getContentTag(): String
}