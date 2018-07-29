package com.luigivampa92.yms.fintracker.data.db.dao

import android.arch.persistence.room.*
import com.luigivampa92.yms.fintracker.data.db.entity.ExchangeRateEntity

@Dao
interface ExchangeRateDao {

    @Query("SELECT * FROM exchange_rate")
    fun getAll(): List<ExchangeRateEntity>

//    @Query("SELECT * FROM exchange_rate WHERE from = :from AND to = :to") // todo
//    fun get(from: String, to: String): ExchangeRateEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exchangeRate: ExchangeRateEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(exchangeRate: ExchangeRateEntity)

    @Delete
    fun delete(exchangeRate: ExchangeRateEntity)

    @Transaction
    fun insertAll(exchangeRates: Collection<ExchangeRateEntity>) {
        exchangeRates.forEach {
            insert(it)
        }
    }
}