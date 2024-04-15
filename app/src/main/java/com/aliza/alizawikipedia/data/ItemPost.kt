package com.aliza.alizawikipedia.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemPost(
    val imgUrl: String,
    val txtTitle: String,
    val txtSubtitle: String,
    val txtDetail: String ,
    val wikiUrl: String ,

    // for trend fragment =>
    val isTrend :Boolean ,
    val insight :String
): Parcelable