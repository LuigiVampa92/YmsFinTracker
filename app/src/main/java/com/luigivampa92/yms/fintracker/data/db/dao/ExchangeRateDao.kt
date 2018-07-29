package com.luigivampa92.yms.fintracker.data.db.dao

import android.arch.persistence.room.*
import com.luigivampa92.yms.fintracker.data.db.entity.ExchangeRateEntity
import io.reactivex.Single

@Dao
interface ExchangeRateDao : BaseDao<ExchangeRateEntity> {

    @Query("SELECT * FROM exchange_rate")
    fun getAll(): Single<List<ExchangeRateEntity>>

//    @Query("SELECT * FROM exchange_rate WHERE from = :from AND to = :to") // todo
//    fun get(from: String, to: String): ExchangeRateEntity

    @Query("DELETE FROM exchange_rate")
    fun deleteAll()

    @Transaction
    fun insertAll(exchangeRates: Collection<ExchangeRateEntity>) {
        deleteAll()
        exchangeRates.forEach {
            insert(it)
        }
    }
}