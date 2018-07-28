package com.luigivampa92.yms.finops.converter.impl

import com.luigivampa92.yms.finops.converter.CurrencyConverter
import com.luigivampa92.yms.finops.model.Currency
import com.luigivampa92.yms.finops.model.OperationType
import com.luigivampa92.yms.finops.model.Record

internal class RurUsdCurrencyConverter(private val exchangeRate: Double) : CurrencyConverter {

    override fun convert(record: Record): Record {
        check(record.currency == Currency.RUR)

        val amount = record.amount.toDouble() * exchangeRate
        val type = if (amount < 0) OperationType.EXPENSE else OperationType.REVENUE
        return Record(type, amount.toInt(), Currency.USD)
    }
}