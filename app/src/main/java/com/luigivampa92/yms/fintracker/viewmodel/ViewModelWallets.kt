package com.luigivampa92.yms.fintracker.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Wallet
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class ViewModelWallets(application: Application) : AndroidViewModel(application) {

    private var mWallets: LiveData<List<Wallet>> = MutableLiveData()
    private val mApplication = application

    fun getWallets(): LiveData<List<Wallet>> {
        val database = FinanceTrackerDatabase.getInstance(mApplication)
        if(database?.walletsDao()?.getAllWallets() != null){
            mWallets = database.walletsDao().getAllWallets()
        }
        return mWallets
    }

}