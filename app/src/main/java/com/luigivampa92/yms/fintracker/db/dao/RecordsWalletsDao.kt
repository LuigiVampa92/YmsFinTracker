package com.luigivampa92.yms.fintracker.db.dao

import android.arch.persistence.room.*
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet

@Dao
interface RecordsWalletsDao{

    @Insert
    fun insertRecord(record: Record)

    @Query("DELETE from records WHERE id = :recordId")
    fun deleteRecord(recordId: String)

    @Update
    fun updateRecord(record: Record)

    @Query("UPDATE wallets SET balance = balance + :recordValue WHERE id =:walletId")
    fun updateWalletBalance(recordValue: Double, walletId: String)

    @Transaction fun updateRecordUpdateWalletBalance(record: Record, walletId: String){
        updateRecord(record)
        updateWalletBalance(record.amount, walletId)
    }

    @Transaction
    fun deleteRecordUpdateWalletBalance(record: Record, walletId: String){
        deleteRecord(record.id)
        updateWalletBalance(-record.amount, walletId)
    }

    @Transaction
    fun insertRecordUpdateWalletBalance(record: Record, walletId: String){
        insertRecord(record)
        updateWalletBalance(record.amount, walletId)
    }

}