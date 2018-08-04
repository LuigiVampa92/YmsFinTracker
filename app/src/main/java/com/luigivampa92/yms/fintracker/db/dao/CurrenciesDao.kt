package com.luigivampa92.yms.fintracker.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.luigivampa92.yms.fintracker.model.Currency

@Dao
interface CurrenciesDao {

    @Insert()
    fun addCurrency(currency: Currency)

    @Query("DELETE from currencies")
    fun deleteAllCurrencies()
}