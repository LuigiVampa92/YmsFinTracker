package com.luigivampa92.yms.fintracker

import android.support.annotation.StringRes
import android.text.Html
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.widget.TextView

fun getString(@StringRes id: Int) = FinTrackerApplication.INSTANCE.getString(id)

fun TextView.setHtml(string: String) {
    val result: Spanned
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        result = Html.fromHtml(string, Html.FROM_HTML_MODE_LEGACY)
    } else {
        result = Html.fromHtml(string)
    }
    this.text = result
    this.movementMethod = LinkMovementMethod.getInstance()
}

fun TextView.setHtml(@StringRes id: Int) {
    this.setHtml(getString(id))
}

// todo fix hw#1F
fun Int.formatAsMoney(): String {
    return String.format("%.02f", this.toDouble() / 100.0).trim().replace(",", ".")
}