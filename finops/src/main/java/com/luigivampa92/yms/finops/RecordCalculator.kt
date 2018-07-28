package com.luigivampa92.yms.finops

import com.luigivampa92.yms.finops.converter.CurrencyConverterFactory
import com.luigivampa92.yms.finops.model.Currency
import com.luigivampa92.yms.finops.model.OperationType
import com.luigivampa92.yms.finops.model.Record

// todo fix hw#1 tests
class RecordCalculator (private val currencyConverterFactory: CurrencyConverterFactory) {

    fun updateCurrencyExchangeRates(pair: Pair<Currency, Currency>, rate: Double) {
        currencyConverterFactory.updateCurrencyExchangeRates(pair, rate)
    }

    fun updateCurrencyExchangeRates(rates: Map<Pair<Currency, Currency>, Double>) {
        currencyConverterFactory.updateCurrencyExchangeRates(rates)
    }

    fun sum(vararg records: Record) : Record {
        check(records.isNotEmpty())
        if (records.size == 1) {
            return records[0]
        }

        var result = records[0]
        for (i in 1 until records.size) {
            val record = records[i]
            val converter = currencyConverterFactory.getConverterForCurrencyPair(result.currency, record.currency)
            val converted = converter.convert(record)

            val amount = if (record.type == OperationType.REVENUE) result.amount + converted.amount else result.amount - converted.amount
            val type = if (amount < 0) OperationType.EXPENSE else OperationType.REVENUE

            result = Record(type, amount, result.currency)
        }

        return result
    }

    fun convert(record: Record, currency: Currency) : Record {
        val converter = currencyConverterFactory.getConverterForCurrencyPair(record.currency, currency)
        return converter.convert(record)
    }
}