package com.luigivampa92.yms.fintracker.db.dao

import android.arch.persistence.room.*
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet

@Dao
interface RecordsWalletsDao {

    @Insert
    fun insertRecord(record: Record)

    @Query("DELETE from records WHERE id = :recordId")
    fun deleteRecord(recordId: String)

    @Update
    fun updateRecord(record: Record)

    @Query("UPDATE wallets SET balance = balance + :recordValue WHERE id =:walletId")
    fun updateWalletBalance(recordValue: Double, walletId: String)

    @Query("DELETE from wallets WHERE id = :walletId")
    fun deleteWallet(walletId: String)

    @Query("DELETE from records WHERE wallet_id = :walletId")
    fun deleteRecordsFromWallet(walletId: String)

    @Transaction
    fun updateRecordUpdateWalletBalance(record: Record, oldRecord: Record) {
        updateWalletBalance(-oldRecord.amount, oldRecord.wallet_id)
        updateRecord(record)
        updateWalletBalance(record.amount, record.wallet_id)
    }

    @Transaction
    fun deleteRecordUpdateWalletBalance(record: Record, walletId: String) {
        deleteRecord(record.id)
        updateWalletBalance(-record.amount, walletId)
    }

    @Transaction
    fun insertRecordUpdateWalletBalance(record: Record) {
        insertRecord(record)
        updateWalletBalance(record.amount, record.wallet_id)
    }

    @Transaction
    fun deleteWalletAndRecords(walletId: String){
        deleteWallet(walletId)
        deleteRecordsFromWallet(walletId)
    }

}