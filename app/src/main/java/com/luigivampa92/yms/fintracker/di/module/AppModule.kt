package com.luigivampa92.yms.fintracker.di.module

import com.luigivampa92.yms.finops.CurrencyConverter
import com.luigivampa92.yms.finops.RecordCalculator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideCurrencyConverter() = CurrencyConverter()

    @Singleton
    @Provides
    fun provideRecordCalculator(currencyConverter: CurrencyConverter) = RecordCalculator(currencyConverter)
}