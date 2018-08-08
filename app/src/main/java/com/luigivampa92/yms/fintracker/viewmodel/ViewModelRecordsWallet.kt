package com.luigivampa92.yms.fintracker.viewmodel

import android.arch.lifecycle.*
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet
import com.luigivampa92.yms.fintracker.model.repositories.Repository

class ViewModelRecordsWallet(repository: Repository) : ViewModel() {

    private val mRepository = repository

    fun getRecordsFromWallet(walletId: String): LiveData<List<Record>>{
        return mRepository.getRecordsFromWallet(walletId)
    }

    fun getWallet(walletId: String): LiveData<Wallet> {
        return mRepository.getWallet(walletId)
    }

    fun deleteRecord(record: Record) {
        mRepository.deleteRecord(record)
    }
}