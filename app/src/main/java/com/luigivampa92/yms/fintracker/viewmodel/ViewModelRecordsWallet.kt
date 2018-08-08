package com.luigivampa92.yms.fintracker.viewmodel

import android.arch.lifecycle.*
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet
import com.luigivampa92.yms.fintracker.model.repositories.RecordsWalletRepository
import kotlinx.coroutines.experimental.launch

class ViewModelRecordsWallet(repository: RecordsWalletRepository) : ViewModel() {

    private val mRecordsWalletRepository = repository

    fun getRecordsFromWallet(walletId: String): LiveData<List<Record>>{
        return mRecordsWalletRepository.getRecordsFromWallet(walletId)
    }

    fun getWallet(walletId: String): LiveData<Wallet> {
        return mRecordsWalletRepository.getWallet(walletId)
    }

    fun deleteRecord(record: Record) {
        mRecordsWalletRepository.deleteRecord(record)
    }
}