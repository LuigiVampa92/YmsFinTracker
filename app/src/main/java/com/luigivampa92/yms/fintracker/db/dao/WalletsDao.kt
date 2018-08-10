package com.luigivampa92.yms.fintracker.db.dao

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.luigivampa92.yms.fintracker.model.Wallet

@Dao
interface WalletsDao {

    @Query("SELECT * from wallets")
    fun getAllWallets(): LiveData<List<Wallet>>

    @Query("SELECT * from wallets WHERE id = :walletId")
    fun getWallet(walletId: String): LiveData<Wallet>

    @Insert()
    fun addWallet(wallet: Wallet)

    @Update
    fun updateWallet(wallet: Wallet)

    @Query("DELETE from wallets")
    fun deleteAllWallets()
}