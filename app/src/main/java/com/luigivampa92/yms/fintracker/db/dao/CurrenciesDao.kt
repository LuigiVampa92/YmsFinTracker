package com.luigivampa92.yms.fintracker.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.luigivampa92.yms.fintracker.model.Currency
import com.luigivampa92.yms.fintracker.model.Record

@Dao
interface CurrenciesDao {

    @Insert()
    fun addCurrency(currency: Currency)

    @Query("SELECT * from currencies WHERE name = :currencyName")
    fun getCurrencyRatioToDollar(currencyName: String): Currency

    @Query("DELETE from currencies")
    fun deleteAllCurrencies()
}