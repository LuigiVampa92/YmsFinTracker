package com.luigivampa92.yms.fintracker.model.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Wallet

class WalletsRepository(application: Application) {

    private val mApplication = application

    fun getWallets(): LiveData<List<Wallet>> {
        var wallets: LiveData<List<Wallet>> = MutableLiveData()
        val database = FinanceTrackerDatabase.getInstance(mApplication)
        if (database?.walletsDao()?.getAllWallets() != null) {
            wallets = database.walletsDao().getAllWallets()
        }
        return wallets
    }
}