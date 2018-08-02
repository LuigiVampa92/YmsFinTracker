package com.luigivampa92.yms.fintracker.model

import java.util.*

data class Record (
        val name: String,
        val category: String,
        val expense: Boolean,
        val amou: Double,
        val currency: String,
        val account: Wallet,
        val date: Date,
        val pending_date: Date
)