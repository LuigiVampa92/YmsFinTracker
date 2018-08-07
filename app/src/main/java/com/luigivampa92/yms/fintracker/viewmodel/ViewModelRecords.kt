package com.luigivampa92.yms.fintracker.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet
import kotlinx.coroutines.experimental.launch

class ViewModelRecords(application: Application) : AndroidViewModel(application) {

    private var mRecords: LiveData<List<Record>> = MutableLiveData()
    private var mWallet: LiveData<Wallet> = MutableLiveData()
    private val mApplication = application

    fun getRecordsFromWallet(walletId: String): LiveData<List<Record>> {
        val database = FinanceTrackerDatabase.getInstance(mApplication)
        if (database?.recordsDao()?.getAllRecordsFromWallet(walletId) != null) {
            mRecords = database.recordsDao().getAllRecordsFromWallet(walletId)
        }
        return mRecords
    }

    fun getWallet(walletId: String): LiveData<Wallet> {
        val database = FinanceTrackerDatabase.getInstance(mApplication)
        if (database?.recordsDao()?.getAllRecordsFromWallet(walletId) != null) {
            mWallet = database.walletsDao().getWallet(walletId)
        }
        return mWallet
    }

    fun deleteRecord(record: Record) {
        launch {
            val database = FinanceTrackerDatabase.getInstance(mApplication)
            database?.recordsWalletsDao()?.deleteRecordUpdateWalletBalance(record, record.wallet_id)
        }
    }
}