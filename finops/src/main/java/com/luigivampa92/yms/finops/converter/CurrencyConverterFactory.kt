package com.luigivampa92.yms.finops.converter

import com.luigivampa92.yms.finops.converter.impl.EqualCurrencyConverter
import com.luigivampa92.yms.finops.converter.impl.UsbRurCurrencyConverter
import com.luigivampa92.yms.finops.model.Currency

// todo make it sealed class ?
class CurrencyConverterFactory {

    private var exchangeRates = HashMap<Pair<Currency, Currency>, Double>()

    internal fun updateCurrencyExchangeRates(pair: Pair<Currency, Currency>, rate: Double) {
        exchangeRates.put(pair, rate)
    }

    internal fun updateCurrencyExchangeRates(rates: Map<Pair<Currency, Currency>, Double>) {
        rates.keys.forEach {
            exchangeRates.putAll(rates)
        }
    }

    // todo annotation/codegen implementation ?
    internal fun getConverterForCurrencyPair(from: Currency, to: Currency): CurrencyConverter {
        return when {
            Currency.USD == from && Currency.RUR == to -> UsbRurCurrencyConverter(exchangeRates[Pair(from, to)] ?: 1.0)
            Currency.RUR == from && Currency.USD == to -> UsbRurCurrencyConverter(exchangeRates[Pair(from, to)] ?: 1.0)
            from == to -> EqualCurrencyConverter()
            else -> throw IllegalArgumentException("No converter for given currency pair")
        }
    }
}