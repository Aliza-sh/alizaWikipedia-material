package com.aliza.alizawikipedia.base

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.aliza.alizawikipedia.base.Constant.LIGHT
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.createSharedPreferences(): SharedPreferences {
    return getSharedPreferences(SHAREDPREFERENCES_APPTHEME, Context.MODE_PRIVATE)
}
fun SharedPreferences.writePref(key: String, value: String) {
    edit().putString(key, value).apply()
}
fun SharedPreferences.readPref(key: String, defaultValue: String = LIGHT): String {
    return getString(key, defaultValue) ?: defaultValue
}

fun Context.showDialog(title: String, detail: String) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(detail)
        .setPositiveButton("Submit") { dialog, which ->
            dialog.dismiss()
        }
        .show()
}

fun Context.copyToClipboard(text: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("url", text)
    clipboard.setPrimaryClip(clip)
}

fun Context.showToast(context: Context, str: String) {
    Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
}