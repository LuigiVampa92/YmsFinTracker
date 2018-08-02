package com.luigivampa92.yms.fintracker

import java.lang.Double.parseDouble


fun isNumeric(string: String): Boolean {
    var numeric = true
    try {
        parseDouble(string)
    } catch (e: NumberFormatException) {
        numeric = false
    }
    return numeric
}