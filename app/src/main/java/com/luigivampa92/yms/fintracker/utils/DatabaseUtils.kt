package com.luigivampa92.yms.fintracker.utils

import android.app.Application
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Currency
import java.util.*

fun saveCurrencies(currencies: MutableList<Currency>, application: Application){
    currencies.forEach {
        FinanceTrackerDatabase.getInstance(application)?.currenciesDao()?.addCurrency(it)
    }
}