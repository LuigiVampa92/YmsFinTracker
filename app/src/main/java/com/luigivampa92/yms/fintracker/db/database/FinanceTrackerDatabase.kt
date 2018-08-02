package com.luigivampa92.yms.fintracker.db.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.luigivampa92.yms.fintracker.db.WalletsDao
import com.luigivampa92.yms.fintracker.model.Wallet

@Database(entities = [(Wallet::class)], version = 1)
abstract class FinanceTrackerDatabase : RoomDatabase() {

    abstract fun walletsDao(): WalletsDao

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