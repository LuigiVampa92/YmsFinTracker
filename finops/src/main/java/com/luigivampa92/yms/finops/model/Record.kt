package com.luigivampa92.yms.finops.model

data class Record (
        val type: OperationType,
        val amount: Int,
        val currency: Currency
)