package com.luigivampa92.yms.fintracker.data

import com.luigivampa92.yms.fintracker.data.db.dao.CurrencyDao
import com.luigivampa92.yms.fintracker.data.db.entity.CurrencyEntity
import com.luigivampa92.yms.fintracker.data.db.dao.ExchangeRateDao
import com.luigivampa92.yms.fintracker.data.db.entity.ExchangeRateEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExchangeRatesDbSource @Inject constructor (
        private val currencyDao: CurrencyDao,
        private val exchangeRateDao: ExchangeRateDao
) {

    fun getAvailableSymbols(): Single<List<CurrencyEntity>> = currencyDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun setSymbols(symbols: Collection<CurrencyEntity>): Completable =
            Completable.fromAction {
                currencyDao.insertAll(symbols)
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    fun getAllExchangeRates(): Single<List<ExchangeRateEntity>> = exchangeRateDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun setExchangeRates(exchangeRates: Collection<ExchangeRateEntity>): Completable =
            Completable.fromAction {
                exchangeRateDao.insertAll(exchangeRates)
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}