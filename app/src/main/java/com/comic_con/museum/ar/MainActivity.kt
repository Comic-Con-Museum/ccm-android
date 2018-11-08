package com.comic_con.museum.ar

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.comic_con.museum.ar.ar.UnityCompatActivity
import com.comic_con.museum.ar.experience.ExperienceActivity

class MainActivity: AppCompatActivity() {

    var toolbar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar = supportActionBar

        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {


            startActivity(ExperienceActivity.createIntent(this))
        }

    }
}