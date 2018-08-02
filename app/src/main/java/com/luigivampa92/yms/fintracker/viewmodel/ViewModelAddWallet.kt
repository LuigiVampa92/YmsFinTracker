package com.luigivampa92.yms.fintracker.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Wallet
import kotlinx.coroutines.experimental.launch

class ViewModelAddWallet(application: Application) : AndroidViewModel(application) {

    private val mApplication = application

    fun addWallet(wallet: Wallet) {
        val database = FinanceTrackerDatabase.getInstance(mApplication)
        launch {
            database?.walletsDao()?.addWallet(wallet)
        }
    }
}