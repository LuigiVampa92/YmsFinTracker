package com.luigivampa92.yms.fintracker.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

class ViewModelAddRecord(application: Application) : AndroidViewModel(application) {

    private val mApplication = application

    fun addRecord(record: Record) {
        launch {
            FinanceTrackerDatabase.getInstance(mApplication)?.recordsDao()?.addRecord(record)
        }
    }

    fun updateWallet(walletId: String, record: Record){
        launch {
            val wallet = FinanceTrackerDatabase.getInstance(mApplication)?.walletsDao()?.getWalletObject(walletId)
        }
    }
}