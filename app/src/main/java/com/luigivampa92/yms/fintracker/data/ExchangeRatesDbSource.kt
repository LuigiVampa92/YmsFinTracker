package com.luigivampa92.yms.fintracker.data

import com.luigivampa92.yms.fintracker.data.db.dao.CurrencyDao
import com.luigivampa92.yms.fintracker.data.db.entity.CurrencyEntity
import com.luigivampa92.yms.fintracker.data.db.dao.ExchangeRateDao
import com.luigivampa92.yms.fintracker.data.db.entity.ExchangeRateEntity
import io.reactivex.Single
import javax.inject.Inject

class ExchangeRatesDbSource @Inject constructor (
        private val currencyDao: CurrencyDao,
        private val exchangeRateDao: ExchangeRateDao
) {

    fun getAvailableSymbols(): Single<List<CurrencyEntity>> {
        return Single.just(currencyDao.getAll())
    }

    fun setSymbols(symbols: Collection<CurrencyEntity>) {
        currencyDao.insertAll(symbols)
    }

    fun getAllExchangeRates(): Single<List<ExchangeRateEntity>> {
        return Single.just(exchangeRateDao.getAll())
    }

    fun setExchangeRates(exchangeRates: Collection<ExchangeRateEntity>) {
        exchangeRateDao.insertAll(exchangeRates)
    }
}