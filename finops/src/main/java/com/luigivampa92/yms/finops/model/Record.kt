package com.luigivampa92.yms.finops.model

import java.util.*

data class Record (
        val type: OperationType,
        val amount: Double,
        val category: Category,
        val time: Date,
        val account: Account,
        val comment: String? = null
)