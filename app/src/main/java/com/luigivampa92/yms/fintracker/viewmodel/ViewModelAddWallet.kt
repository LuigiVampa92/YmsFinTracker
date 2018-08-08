package com.luigivampa92.yms.fintracker.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Wallet
import com.luigivampa92.yms.fintracker.model.repositories.Repository
import kotlinx.coroutines.experimental.launch

class ViewModelAddWallet(repository: Repository) : ViewModel() {

    private val mRepository = repository

    fun addWallet(wallet: Wallet) {
        wallet.balance = CurrencyConverter.convertCurrency(wallet.currency, wallet.balance)
        mRepository.addWallet(wallet)
    }
}