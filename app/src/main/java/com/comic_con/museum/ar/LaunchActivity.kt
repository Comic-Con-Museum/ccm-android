package com.comic_con.museum.ar

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import com.comic_con.museum.ar.loading.LoadingScreenFragment

class LaunchActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        switchToFragment(LoadingScreenFragment(), "loading_screen")
    }

    fun finishLoading() {
        // Move to the main activity
        val mainActivityIntent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(mainActivityIntent)
    }

    private fun switchToFragment(fragment: Fragment, tag: String?) {
        val transaction = supportFragmentManager?.beginTransaction() ?: return
        transaction.replace(R.id.content_frame, fragment)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish()
            true
        } else super.onKeyDown(keyCode, event)
    }


}