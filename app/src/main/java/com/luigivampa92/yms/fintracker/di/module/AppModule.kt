package com.luigivampa92.yms.fintracker.di.module

import android.content.Context
import com.luigivampa92.yms.finops.RecordCalculator
import com.luigivampa92.yms.finops.converter.CurrencyConverterFactory
import com.luigivampa92.yms.fintracker.ContactRouter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContactRouter(context: Context) = ContactRouter(context)

    @Singleton
    @Provides
    fun provideCurrencyConverterFactory() = CurrencyConverterFactory()

    @Singleton
    @Provides
    fun provideRecordCalculator(currencyConverterFactory: CurrencyConverterFactory) = RecordCalculator(currencyConverterFactory)
}