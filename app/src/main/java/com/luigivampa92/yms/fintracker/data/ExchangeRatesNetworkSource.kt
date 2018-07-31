package com.luigivampa92.yms.fintracker.data

import android.text.TextUtils
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.data.network.ExchangeRatesApi
import com.luigivampa92.yms.fintracker.data.network.error.NetworkErrorMapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExchangeRatesNetworkSource @Inject constructor (
        private val api: ExchangeRatesApi,
        private val networkErrorMapper: NetworkErrorMapper
) {

    fun getExchangeRates() = api.getLatest(getQueryParams())
            .onErrorResumeNext { Single.error(networkErrorMapper.map(it)) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getAvailableSymbols() = api.getSymbols()
            .onErrorResumeNext { Single.error(networkErrorMapper.map(it)) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private fun getQueryParams() = mapOf<String,String>(
            "symbols" to TextUtils.join(",", Constants.supportedCurrencies)
    )
}