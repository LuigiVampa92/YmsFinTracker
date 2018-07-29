package com.luigivampa92.yms.fintracker.data.db.dao

import android.arch.persistence.room.*
import com.luigivampa92.yms.fintracker.data.db.entity.CurrencyEntity
import io.reactivex.Single

@Dao
interface CurrencyDao : BaseDao<CurrencyEntity> {

    @Query("SELECT * FROM currency")
    fun getAll(): Single<List<CurrencyEntity>>

    @Query("SELECT * FROM currency WHERE ticker = :ticker")
    fun getById(ticker: String): Single<CurrencyEntity>

    @Query("DELETE FROM currency")
    fun deleteAll()

    @Transaction
    fun insertAll(currencies: Collection<CurrencyEntity>) {
        deleteAll()
        currencies.forEach {
            insert(it)
        }
    }
}