package com.luigivampa92.yms.fintracker.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet

class ViewModelRecords(application: Application) : AndroidViewModel(application) {

    private var mRecords: LiveData<List<Record>> = MutableLiveData()
    private val mApplication = application

    fun getRecordsFromWallet(walletName: String): LiveData<List<Record>> {
        val database = FinanceTrackerDatabase.getInstance(mApplication)
        if (database?.recordsDao()?.getAllRecordsFromWallet(walletName) != null) {
            mRecords = database.recordsDao().getAllRecordsFromWallet(walletName)
        }
        return mRecords
    }
}