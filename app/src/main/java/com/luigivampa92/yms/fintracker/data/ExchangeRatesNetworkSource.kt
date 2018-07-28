package com.luigivampa92.yms.fintracker.data

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExchangeRatesNetworkSource @Inject constructor (
        private val api: ExchangeRatesApi
) {

    fun getExchangeRates() {
        api.getLatest(getQueryParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            val a = "a"
                        },
                        {
                            val a = "a"
                        }
                )
    }

    fun getAvailableSymbols() {
        api.getSymbols()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            val a = "a"
                        },
                        {
                            val a = "a"
                        }
                )
    }

    private fun getQueryParams() = mapOf<String,String>(
            "symbols" to "USD,EUR,RUR"
    )
}