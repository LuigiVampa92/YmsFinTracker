package com.luigivampa92.yms.fintracker.model

import java.util.*

data class Record (
        val amount: Double,
        val expense: Boolean,
        val category: String,
        val time: Date,
        val account: Account
)