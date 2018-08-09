package com.luigivampa92.yms.fintracker.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet

@Dao
interface RecordsDao {

    @Query("SELECT * from records WHERE wallet_id = :walletId")
    fun getAllRecordsFromWallet(walletId: String): LiveData<List<Record>>

    @Insert(onConflict = REPLACE)
    fun addRecord(record: Record)

    @Delete
    fun deleteRecord(record: Record)

    @Query("SELECT * from records")
    fun getAllRecords(): LiveData<List<Record>>

    @Query("DELETE from records")
    fun deleteAllRecords()
}