package com.comic_con.museum.ar.ar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.comicconmuseum.ccmaugmentedreality.UnityPlayerActivity

class UnityCompatActivity: AppCompatActivity() {

    /**
     * Unity closes the housing activity that launched the UnityPlayer Intent, so
     * that intent needs to be wrapped in its own activity to prevent oddities
     * when closing the Unity app.
     */

    override fun onResume() {
        super.onResume()
        val arIntent = Intent(this, UnityPlayerActivity::class.java)
        startActivity(arIntent)
    }
}