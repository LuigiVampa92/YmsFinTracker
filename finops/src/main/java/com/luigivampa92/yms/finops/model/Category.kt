package com.luigivampa92.yms.finops.model

data class Category (
        val operationType: OperationType,
        val name: String,
        val parent: Category? = null
)