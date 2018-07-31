package com.luigivampa92.yms.finops

import com.luigivampa92.yms.finops.model.Balance
import com.luigivampa92.yms.finops.model.Currency
import com.luigivampa92.yms.finops.model.OperationType
import com.luigivampa92.yms.finops.model.Record
import java.util.*

// todo fix hw#1 tests
class RecordCalculator (private val currencyConverter: CurrencyConverter) {

    fun updateCurrencyExchangeRates(pair: Pair<Currency, Currency>, rate: Double) {
        currencyConverter.updateCurrencyExchangeRates(pair, rate)
    }

    fun updateCurrencyExchangeRates(rates: Map<Pair<Currency, Currency>, Double>) {
        currencyConverter.updateCurrencyExchangeRates(rates)
    }

    fun sum(vararg records: Record) : Balance {
        check(records.isNotEmpty())
        if (records.size == 1) {
            return Balance(records[0].amount, records[0].account.currency)
        }

        var result = records[0]
        for (i in 1 until records.size) {
            val record = records[i]
            val converted = currencyConverter.convert(record.amount, record.account.currency, result.account.currency)

            val amount = if (record.type == OperationType.IN) result.amount + converted else result.amount - converted
            val type = if (amount < 0) OperationType.OUT else OperationType.IN

            result = Record(type, amount, result.category, Date(), result.account)
        }

        return Balance(result.amount, result.account.currency)
    }

    fun convert(record: Record, currency: Currency) : Balance {
        val converted = currencyConverter.convert(record.amount, record.account.currency, currency)
        return Balance(converted, currency)
    }
}