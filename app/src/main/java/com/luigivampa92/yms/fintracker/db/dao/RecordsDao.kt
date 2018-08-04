package com.luigivampa92.yms.fintracker.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet

@Dao
interface RecordsDao {

    @Query("SELECT * from records WHERE wallet_id = :walletId")
    fun getAllRecordsFromWallet(walletId: String): LiveData<List<Record>>

    @Query("SELECT * from wallets WHERE id = :walletId")
    fun getWallet(walletId: String): LiveData<Wallet>

    @Insert()
    fun addRecord(record: Record)

    @Query("DELETE from records")
    fun deleteAllRecords()
}