package com.comic_con.museum.ar

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import com.comic_con.museum.ar.experience.ExperienceActivity
import com.comic_con.museum.ar.overview.OverviewFragment
import com.comic_con.museum.ar.overview.OverviewViewModel
import com.comic_con.museum.ar.views.ExhibitCard
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    @Inject
    lateinit var overviewViewModel: OverviewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CCMApplication.getApplication().injectorComponent.inject(this)

        setContentView(R.layout.activity_main)

        overviewViewModel.addExhibitModel(resources.openRawResource(R.raw.experience_may_fourth))
        overviewViewModel.addExhibitModel(resources.openRawResource(R.raw.experience_eisners))

        switchToFragment(OverviewFragment(), "overview")
    }

    fun switchToFragment(fragment: Fragment, tag: String?) {
        val transaction = supportFragmentManager?.beginTransaction() ?: return
        transaction.replace(R.id.content_frame, fragment)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

    fun beginExperienceActivity(experienceId: String) {
        val newExperienceIntent = ExperienceActivity.createIntent(this)
        newExperienceIntent.putExtra(ExhibitCard.EXPERIENCE_ID_KEY, experienceId)
        this.startActivity(newExperienceIntent)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish()
            true
        } else super.onKeyDown(keyCode, event)
    }
}