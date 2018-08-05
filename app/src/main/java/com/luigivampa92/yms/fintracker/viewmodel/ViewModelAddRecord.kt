package com.luigivampa92.yms.fintracker.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import kotlinx.coroutines.experimental.launch

class ViewModelAddRecord(application: Application) : AndroidViewModel(application) {

    private val mApplication = application

    fun addRecord(record: Record) {
        launch {
            FinanceTrackerDatabase.getInstance(mApplication)?.recordsDao()?.addRecord(record)
        }
    }

    fun updateWallet(walletId: String, balance: Double){
        launch {
            val database = FinanceTrackerDatabase.getInstance(mApplication)
            database?.walletsDao()?.updateWalletBalance(walletId, balance)
        }
    }

}