package com.luigivampa92.yms.fintracker.db.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.luigivampa92.yms.fintracker.db.dao.*
import com.luigivampa92.yms.fintracker.model.Currency
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Template
import com.luigivampa92.yms.fintracker.model.Wallet

@Database(entities = [(Wallet::class), (Record::class), (Currency::class), (Template::class)], version = 1)
abstract class FinanceTrackerDatabase : RoomDatabase() {

    abstract fun walletsDao(): WalletsDao
    abstract fun recordsDao(): RecordsDao
    abstract fun currenciesDao(): CurrenciesDao
    abstract fun recordsWalletsDao(): RecordsWalletsDao
    abstract fun templatesDao(): TemplatesDao

    companion object {
        private var INSTANCE: FinanceTrackerDatabase? = null

        fun getInstance(context: Context): FinanceTrackerDatabase?{
            if(INSTANCE == null){
                synchronized(FinanceTrackerDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            FinanceTrackerDatabase::class.java, "finance_tracker.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}