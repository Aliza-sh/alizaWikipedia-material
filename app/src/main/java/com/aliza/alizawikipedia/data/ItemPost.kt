package com.aliza.alizawikipedia.data

data class ItemPost(
    val imgUrl: String,
    val txtTitle: String,
    val txtSubtitle: String,
    val txtDetail: String ,

    // for trend fragment =>
    val isTrend :Boolean ,
    val insight :String
)