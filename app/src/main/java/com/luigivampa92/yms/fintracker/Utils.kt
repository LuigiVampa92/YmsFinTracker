package com.luigivampa92.yms.fintracker

import android.widget.TextView
import java.lang.Double.parseDouble
import java.text.DecimalFormat
import java.util.*


fun isNumeric(string: String): Boolean {
    var numeric = true
    try {
        parseDouble(string)
    } catch (e: NumberFormatException) {
        numeric = false
    }
    return numeric
}


@Throws(Exception::class)
fun createId(): String {
    return UUID.randomUUID().toString().replace("-", "").toUpperCase()
}

fun hasText(vararg views: TextView): Boolean {
    views.forEach {
        if (getTextFromView(it).isEmpty()) return false
    }
    return true
}

fun getTextFromView(view: TextView): String {
    return view.text.toString()
}

fun formatDecimalNumber(value: Double): String {
    return DecimalFormat("0.00").format(value)
}