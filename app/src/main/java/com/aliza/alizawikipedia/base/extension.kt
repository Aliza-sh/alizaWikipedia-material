package com.aliza.alizawikipedia.base

import android.content.Context
import android.content.SharedPreferences
import com.aliza.alizawikipedia.base.Constant.LIGHT

fun Context.createSharedPreferences(): SharedPreferences {
    return getSharedPreferences(SHAREDPREFERENCES_APPTHEME, Context.MODE_PRIVATE)
}
fun SharedPreferences.writePref(key: String, value: String) {
    edit().putString(key, value).apply()
}
fun SharedPreferences.readPref(key: String, defaultValue: String = LIGHT): String {
    return getString(key, defaultValue) ?: defaultValue
}