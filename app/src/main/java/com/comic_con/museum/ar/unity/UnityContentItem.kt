package com.comic_con.museum.ar.unity

import android.content.res.TypedArray
import android.os.Parcelable
import com.comic_con.museum.ar.overview.ContentItem
import kotlinx.android.parcel.Parcelize

@Parcelize
class UnityContentItem(
    val contentItem: ContentItem?,
    val image: ByteArray
): Parcelable