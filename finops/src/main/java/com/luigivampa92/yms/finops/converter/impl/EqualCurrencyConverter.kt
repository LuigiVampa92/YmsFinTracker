package com.luigivampa92.yms.finops.converter.impl

import com.luigivampa92.yms.finops.converter.CurrencyConverter
import com.luigivampa92.yms.finops.model.Record

internal class EqualCurrencyConverter : CurrencyConverter {
    override fun convert(record: Record) = record
}