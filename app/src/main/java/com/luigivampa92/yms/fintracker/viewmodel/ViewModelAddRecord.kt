package com.luigivampa92.yms.fintracker.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet
import kotlinx.coroutines.experimental.launch

class ViewModelAddRecord(application: Application) : AndroidViewModel(application) {

    private val mApplication = application

    fun addRecord(record: Record) {
        val database = FinanceTrackerDatabase.getInstance(mApplication)
        launch {
            database?.recordsDao()?.addRecord(record)
        }
    }
}