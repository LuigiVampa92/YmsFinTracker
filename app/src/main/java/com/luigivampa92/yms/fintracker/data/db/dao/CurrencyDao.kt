package com.luigivampa92.yms.fintracker.data.db.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.luigivampa92.yms.fintracker.data.db.entity.CurrencyEntity

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency")
    fun getAll(): List<CurrencyEntity>

    @Query("SELECT * FROM currency WHERE ticker = :ticker")
    fun getById(ticker: String): CurrencyEntity

    @Insert(onConflict = REPLACE)
    fun insert(currency: CurrencyEntity)

    @Update(onConflict = REPLACE)
    fun update(currency: CurrencyEntity)

    @Delete
    fun delete(currency: CurrencyEntity)

    @Transaction
    fun insertAll(currencies: Collection<CurrencyEntity>) {
        currencies.forEach {
            insert(it)
        }
    }
}