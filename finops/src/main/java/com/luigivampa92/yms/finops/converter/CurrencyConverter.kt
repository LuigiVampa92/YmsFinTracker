package com.luigivampa92.yms.finops.converter

import com.luigivampa92.yms.finops.model.Record

// todo simplify to universal converter ?
internal interface CurrencyConverter {
    fun convert(record: Record): Record
}