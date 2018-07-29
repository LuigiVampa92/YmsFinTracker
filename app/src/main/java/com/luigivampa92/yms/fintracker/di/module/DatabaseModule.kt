package com.luigivampa92.yms.fintracker.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.luigivampa92.yms.fintracker.data.db.FinTrackerDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) =
            Room.databaseBuilder(context, FinTrackerDatabase::class.java, "database")
                    .build()

    @Provides
    fun provideCurrencyDao(finTrackerDatabase: FinTrackerDatabase) = finTrackerDatabase.currencyDao()

    @Provides
    fun provideExchangeRateDao(finTrackerDatabase: FinTrackerDatabase) = finTrackerDatabase.exchangeRateDao()
}