package com.luigivampa92.yms.finops

import com.luigivampa92.yms.finops.model.Currency

class CurrencyConverter {

    private var exchangeRates = HashMap<Pair<Currency, Currency>, Double>()

    internal fun updateCurrencyExchangeRates(pair: Pair<Currency, Currency>, rate: Double) {
        exchangeRates.put(pair, rate)
    }

    internal fun updateCurrencyExchangeRates(rates: Map<Pair<Currency, Currency>, Double>) {
        rates.keys.forEach {
            exchangeRates.putAll(rates)
        }
    }

    fun convert(amount: Double, from: Currency, to: Currency): Double {
        val mul = exchangeRates[Pair(from, to)] ?: throw IllegalArgumentException("unknown currency")
        return amount * mul
    }
}