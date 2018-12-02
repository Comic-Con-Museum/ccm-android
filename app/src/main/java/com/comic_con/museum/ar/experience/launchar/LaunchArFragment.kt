package com.comic_con.museum.ar.experience.launchar

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.experience.ExperienceActivity
import com.unity3d.player.UnityPlayer

class LaunchArFragment: Fragment() {

    private var rootView: View? = null

    private var unityPlayer: UnityPlayer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_launch_ar, container, false)
        this.rootView = rootView

        this.unityPlayer = (this.activity as ExperienceActivity).unityPlayer
        val unityPlayerView = (this.activity as ExperienceActivity).unityPlayer.view
        unityPlayerView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        (unityPlayerView?.parent as? ViewGroup)?.removeAllViews()

        rootView?.findViewById<ViewGroup>(R.id.unity_player)?.addView(unityPlayerView)

        return rootView
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