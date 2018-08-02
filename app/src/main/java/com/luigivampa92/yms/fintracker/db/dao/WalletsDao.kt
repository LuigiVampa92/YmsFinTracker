package com.luigivampa92.yms.fintracker.db.dao

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.luigivampa92.yms.fintracker.model.Wallet

@Dao
interface WalletsDao {

    @Query("SELECT * from wallets")
    fun getAllWallets(): LiveData<List<Wallet>>

    @Insert(onConflict = REPLACE)
    fun addWallet(wallet: Wallet)

    @Query("DELETE from wallets")
    fun deleteAll()
}