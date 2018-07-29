package com.luigivampa92.yms.fintracker.data

import com.luigivampa92.yms.fintracker.data.db.entity.ExchangeRateEntity
import com.luigivampa92.yms.fintracker.data.network.model.ExchangeRatesModel
import com.luigivampa92.yms.fintracker.domain.ExchangeRate
import com.luigivampa92.yms.fintracker.domain.ExchangeRatesRepository
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class ExchangeRatesRepositoryImpl @Inject constructor (
        private val networkSource: ExchangeRatesNetworkSource,
        private val dbSource: ExchangeRatesDbSource,
        private val networkToDomainMapper: Mapper<ExchangeRatesModel,List<ExchangeRate>>
) : ExchangeRatesRepository {

    override fun getExchangeRates(): Single<List<ExchangeRate>> {
        return dbSource.getAllExchangeRates()
                .flatMap {
                    if (it.isNotEmpty() && !isStale(it.first().updateTime))
                        Single.just(it.map { ExchangeRate(it.from, it.to, it.value) })
                    else
                        getExchangeRatesFromNetwork()
                                .doOnSuccess { dbSource.setExchangeRates(it.map { ExchangeRateEntity(it.from, it.to, it.value, Date()) }).subscribe {} }
                }
    }

    private fun getExchangeRatesFromNetwork(): Single<List<ExchangeRate>> =
            networkSource.getExchangeRates()
                .flatMap { Single.just(networkToDomainMapper.map(it)) }

    private fun isStale(date: Date): Boolean {
        val threshold = 1000 * 60 * 60 * 24
        val diff = Math.abs(Date().time - date.time)
        return diff > threshold
    }
}
