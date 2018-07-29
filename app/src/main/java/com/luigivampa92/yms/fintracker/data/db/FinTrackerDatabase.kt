package com.luigivampa92.yms.fintracker.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.luigivampa92.yms.fintracker.data.db.dao.CurrencyDao
import com.luigivampa92.yms.fintracker.data.db.dao.ExchangeRateDao
import com.luigivampa92.yms.fintracker.data.db.entity.CurrencyEntity
import com.luigivampa92.yms.fintracker.data.db.entity.ExchangeRateEntity

@Database(entities = arrayOf(
        CurrencyEntity::class,
        ExchangeRateEntity::class
), version = 1, exportSchema = false)
@TypeConverters(RoomTypeConverters::class)
abstract class FinTrackerDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
    abstract fun exchangeRateDao(): ExchangeRateDao
}