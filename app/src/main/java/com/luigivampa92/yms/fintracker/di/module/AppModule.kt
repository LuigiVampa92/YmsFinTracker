package com.luigivampa92.yms.fintracker.di.module

import com.luigivampa92.yms.finops.RecordCalculator
import com.luigivampa92.yms.finops.converter.CurrencyConverterFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideCurrencyConverterFactory() = CurrencyConverterFactory()

    @Singleton
    @Provides
    fun provideRecordCalculator(currencyConverterFactory: CurrencyConverterFactory) = RecordCalculator(currencyConverterFactory)
}