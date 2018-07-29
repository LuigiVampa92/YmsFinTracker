package com.luigivampa92.yms.fintracker.di.module

import com.luigivampa92.yms.fintracker.data.*
import com.luigivampa92.yms.fintracker.data.network.ExchangeRatesApi
import com.luigivampa92.yms.fintracker.data.db.dao.CurrencyDao
import com.luigivampa92.yms.fintracker.data.db.dao.ExchangeRateDao
import com.luigivampa92.yms.fintracker.data.network.error.NetworkErrorMapper
import com.luigivampa92.yms.fintracker.data.network.model.ExchangeRatesModel
import com.luigivampa92.yms.fintracker.domain.ExchangeRate
import com.luigivampa92.yms.fintracker.domain.ExchangeRatesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideExchangeRatesNetworkSource(api: ExchangeRatesApi, errorMapper: NetworkErrorMapper) = ExchangeRatesNetworkSource(api, errorMapper)

    @Singleton
    @Provides
    fun provideExchangeRatesDatabaseSource(currencyDao: CurrencyDao, exchangeRateDao: ExchangeRateDao) = ExchangeRatesDbSource(currencyDao, exchangeRateDao)

    @Singleton
    @Provides
    fun provideExchangeRatesRepositoryImpl(networkSource: ExchangeRatesNetworkSource, dbSource: ExchangeRatesDbSource, networkToDomainMapper: ExchangeRatesModelToDomainMapper): ExchangeRatesRepository =
            ExchangeRatesRepositoryImpl(networkSource, dbSource, networkToDomainMapper)

    @Singleton
    @Provides
    fun provideExchangeRatesModelToDomainMapper(): Mapper<ExchangeRatesModel, List<ExchangeRate>> = ExchangeRatesModelToDomainMapper()
}