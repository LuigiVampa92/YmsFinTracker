package com.luigivampa92.yms.fintracker.di.module

import com.luigivampa92.yms.fintracker.data.network.ExchangeRatesApi
import com.luigivampa92.yms.fintracker.data.ExchangeRatesDbSource
import com.luigivampa92.yms.fintracker.data.ExchangeRatesNetworkSource
import com.luigivampa92.yms.fintracker.data.db.dao.CurrencyDao
import com.luigivampa92.yms.fintracker.data.db.dao.ExchangeRateDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideExchangeRatesNetworkSource(api: ExchangeRatesApi) = ExchangeRatesNetworkSource(api)

    @Singleton
    @Provides
    fun provideExchangeRatesDatabaseSource(currencyDao: CurrencyDao, exchangeRateDao: ExchangeRateDao) = ExchangeRatesDbSource(currencyDao, exchangeRateDao)
}