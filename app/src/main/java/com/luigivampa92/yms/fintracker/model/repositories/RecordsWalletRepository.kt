package com.luigivampa92.yms.fintracker.model.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet
import kotlinx.coroutines.experimental.launch

class RecordsWalletRepository(application: Application){

    private val mApplication = application

    fun getRecordsFromWallet(walletId: String): LiveData<List<Record>> {
        var records: LiveData<List<Record>> = MutableLiveData()
        val database = FinanceTrackerDatabase.getInstance(mApplication)
        if (database?.recordsDao()?.getAllRecordsFromWallet(walletId) != null) {
            records = database.recordsDao().getAllRecordsFromWallet(walletId)
        }
        return records
    }

    fun getWallet(walletId: String): LiveData<Wallet> {
        var wallet: LiveData<Wallet> = MutableLiveData()
        val database = FinanceTrackerDatabase.getInstance(mApplication)
        if (database?.recordsDao()?.getAllRecordsFromWallet(walletId) != null) {
            wallet = database.walletsDao().getWallet(walletId)
        }
        return wallet
    }

    fun deleteRecord(record: Record) {
        launch {
            val database = FinanceTrackerDatabase.getInstance(mApplication)
            database?.recordsWalletsDao()?.deleteRecordUpdateWalletBalance(record, record.wallet_id)
        }
    }
}