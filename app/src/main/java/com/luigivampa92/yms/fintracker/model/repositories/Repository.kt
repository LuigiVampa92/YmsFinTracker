package com.luigivampa92.yms.fintracker.model.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet
import kotlinx.coroutines.experimental.launch

class Repository(application: Application){

    //На одном экране нужны будут два репозитория, поэтому я решил слить все в один
    private val mApplication = application


    //Операции с записями
    fun addRecord(record: Record) {
        launch {
            FinanceTrackerDatabase.getInstance(mApplication)?.recordsWalletsDao()?.insertRecordUpdateWalletBalance(record)
        }
    }

    fun editRecord(record: Record, oldRecord: Record){
        launch {
            FinanceTrackerDatabase.getInstance(mApplication)?.recordsWalletsDao()?.updateRecordUpdateWalletBalance(record, oldRecord)
        }
    }

    fun deleteRecord(record: Record) {
        launch {
            val database = FinanceTrackerDatabase.getInstance(mApplication)
            database?.recordsWalletsDao()?.deleteRecordUpdateWalletBalance(record, record.wallet_id)
        }
    }

    fun getRecordsFromWallet(walletId: String): LiveData<List<Record>> {
        var records: LiveData<List<Record>> = MutableLiveData()
        val database = FinanceTrackerDatabase.getInstance(mApplication)
        if (database?.recordsDao()?.getAllRecordsFromWallet(walletId) != null) {
            records = database.recordsDao().getAllRecordsFromWallet(walletId)
        }
        return records
    }


    //Операции с кошельками
    fun getWallets(): LiveData<List<Wallet>> {
        var wallets: LiveData<List<Wallet>> = MutableLiveData()
        val database = FinanceTrackerDatabase.getInstance(mApplication)
        if (database?.walletsDao()?.getAllWallets() != null) {
            wallets = database.walletsDao().getAllWallets()
        }
        return wallets
    }

    fun getWallet(walletId: String): LiveData<Wallet> {
        var wallet: LiveData<Wallet> = MutableLiveData()
        val database = FinanceTrackerDatabase.getInstance(mApplication)
        if (database?.recordsDao()?.getAllRecordsFromWallet(walletId) != null) {
            wallet = database.walletsDao().getWallet(walletId)
        }
        return wallet
    }

}