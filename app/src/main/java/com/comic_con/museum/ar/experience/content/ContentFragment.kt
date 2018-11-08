package com.comic_con.museum.ar.experience.content

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comic_con.museum.ar.R
import com.comic_con.museum.ar.views.ContentCategoryGridView

class ContentFragment: Fragment() {

    private var rootView: View? = null

    private val CONTENT_MODEL = ContentModel(
        "eisners",
        listOf(
            Category(
                "1",
                "Best Of",
                "The best of the eisner awards",
                "http://media.comicbook.com/2016/07/eisners-191821.jpg"
            ),
            Category(
                "2",
                "Best Short Story",
                "The best of the eisner awards",
                "https://magazine.columbia.edu/sites/default/files/2018-09/KG-for-title-page.jpg"
            ),
            Category(
                "3",
                "Best New Series",
                "The best of the eisner awards",
                "https://upload.wikimedia.org/wikipedia/en/4/4e/WarofKings-5.jpg"
            ),
            Category(
                "4",
                "Best Writer",
                "The best of the eisner awards",
                "https://imagecomics.com/uploads/releases/Monstress_14-1.png"
            ),
            Category(
                "5",
                "Best Coloring",
                "The best of the eisner awards",
                "https://upload.wikimedia.org/wikipedia/en/6/6d/My_Favorite_Thing_Is_Monsters_cover.jpg"
            ),
            Category(
                "6",
                "Best Digital Comic",
                "The best of the eisner awards",
                "https://images-na.ssl-images-amazon.com/images/S/cmx-images-prod/Item/434069/434069._SX270_CLs%7C270,422%7Ccu/cmx-cu-sash-lg.png%7C0,0,271,423%20158,310,112,112_QL80_TTD_.jpg"
            ),
            Category(
                "7",
                "Best Lettering",
                "The best of the eisner awards",
                "https://d2lzb5v10mb0lj.cloudfront.net/covers/300/30/3000071.jpg"
            ),
            Category(
                "7",
                "Best Lettering",
                "The best of the eisner awards",
                "https://d2lzb5v10mb0lj.cloudfront.net/covers/300/30/3000071.jpg"
            ),
            Category(
                "7",
                "Best Lettering",
                "The best of the eisner awards",
                "https://d2lzb5v10mb0lj.cloudfront.net/covers/300/30/3000071.jpg"
            )
        ),
        listOf(
            /* No content yet */
        )
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_content, container, false)
        this.rootView = rootView

        rootView?.findViewById<ContentCategoryGridView>(R.id.category_grid)?.setUp(CONTENT_MODEL)

        return rootView
    }
}