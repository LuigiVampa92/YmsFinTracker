package com.luigivampa92.yms.fintracker.calculations

import android.app.Application
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Currency
import com.luigivampa92.yms.fintracker.model.Record
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.coroutines.experimental.bg

object CurrencyConverter {
    fun convertCurrency(currency: String, currencyAmount: Double, currencyConvertTo: String): Double {
        val currencyRatio = Currencies.getCurrencyRatio(currency)
        val currencyToConvertRatio = Currencies.getCurrencyRatio(currencyConvertTo)
        return currencyAmount / currencyRatio * currencyToConvertRatio
    }
}
