package com.luigivampa92.yms.fintracker.calculations

import com.luigivampa92.yms.fintracker.model.Currency

object Currencies {

    val currencies = mutableListOf<Currency>()

    fun getCurrencyRatio(currencyName: String): Double {
        currencies.forEach {
            if (it.name == currencyName) return it.usd_ratio
        }
        return 1.0
    }

}