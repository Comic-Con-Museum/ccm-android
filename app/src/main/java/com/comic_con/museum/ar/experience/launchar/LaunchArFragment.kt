package com.comic_con.museum.ar.experience.launchar

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.ExperienceActivity
import com.unity3d.player.UnityPlayer

class LaunchArFragment: Fragment() {

    private var rootView: View? = null

    private var unityPlayer: UnityPlayer? = null

    private var launchArOnClickEnabled = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_launch_ar, container, false)
        this.rootView = rootView

        // Get UnityPlayer from activity
        this.unityPlayer = (this.activity as? ExperienceActivity)?.unityPlayer
        this.unityPlayer?.resume()

        rootView?.findViewById<Button>(R.id.launch_ar_button)?.setOnClickListener{
            if( !launchArOnClickEnabled ) return@setOnClickListener
            // prevent multiple onClicks
            launchArOnClickEnabled = false
            Handler().postDelayed({
                launchArOnClickEnabled = true
            }, 1000)
            startUnityComponent()
        }

        return rootView
    }

    private fun startUnityComponent() {
        // Prepare the UnityPlayer view for placement on the screen
        val unityPlayerView = (this.activity as ExperienceActivity).unityPlayer.view
        unityPlayerView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        // Place the view on the screen
        rootView?.findViewById<ViewGroup>(R.id.content_root)?.removeAllViews()
        rootView?.findViewById<ViewGroup>(R.id.content_root)?.addView(unityPlayerView)
    }

    fun finishLoading() {
        this.rootView?.findViewById<View>(R.id.progressBar)?.visibility = View.INVISIBLE
        this.rootView?.findViewById<View>(R.id.launch_ar_button)?.let { button ->
            button.alpha = 1f
            button.isClickable = true
            button.isEnabled = true
        }
    }

    override fun onPause() {
        super.onPause()
        unityPlayer?.pause()
    }

    override fun onResume() {
        super.onResume()
        unityPlayer?.resume()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        unityPlayer?.lowMemory()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        unityPlayer?.configurationChanged(newConfig)
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean) {
        super.onMultiWindowModeChanged(isInMultiWindowMode)
        unityPlayer?.windowFocusChanged(isInMultiWindowMode)
    }

    override fun onDestroy() {
        super.onDestroy()
//        unityPlayer?.quit()
    }

}