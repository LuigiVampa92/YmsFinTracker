package com.luigivampa92.yms.fintracker.model.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet

class RecordsRepository(application: Application){

    private val mApplication = application

    fun getRecordsFromWallet(walletId: String): LiveData<List<Record>> {
        var records: LiveData<List<Record>> = MutableLiveData()
        val database = FinanceTrackerDatabase.getInstance(mApplication)
        if (database?.recordsDao()?.getAllRecordsFromWallet(walletId) != null) {
            records = database.recordsDao().getAllRecordsFromWallet(walletId)
        }
        return records
    }
}