package com.luigivampa92.yms.fintracker.domain

import io.reactivex.Single

interface ExchangeRatesRepository {
    fun getExchangeRates(): Single<List<ExchangeRate>>
}