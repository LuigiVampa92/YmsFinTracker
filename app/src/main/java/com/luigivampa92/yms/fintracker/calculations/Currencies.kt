package com.luigivampa92.yms.fintracker.calculations

import com.luigivampa92.yms.fintracker.model.Currency
import com.luigivampa92.yms.fintracker.utils.loadJsonFromAssets
import kotlinx.coroutines.experimental.launch

object Currencies {

    val currencies = mutableListOf<Currency>()
    //to dollar
    fun getCurrencyRatio(currencyName: String): Double {
        currencies.forEach {
            if (it.name == currencyName) return it.usd_ratio
        }
        return 62.5
    }

}