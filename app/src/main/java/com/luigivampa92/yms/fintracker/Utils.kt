package com.luigivampa92.yms.fintracker

import java.lang.Double.parseDouble
import java.util.*
import java.util.UUID.randomUUID
import java.util.Collections.replaceAll




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
fun createRecordId(): String {
    return UUID.randomUUID().toString().replace("-", "").toUpperCase()
}