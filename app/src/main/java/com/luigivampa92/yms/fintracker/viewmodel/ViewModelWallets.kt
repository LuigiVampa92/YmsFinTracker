package com.luigivampa92.yms.fintracker.viewmodel

import android.arch.lifecycle.*
import com.luigivampa92.yms.fintracker.model.repositories.Repository

class ViewModelWallets(repository: Repository) : ViewModel() {

    private val mRepository = repository

    val wallets = mRepository.getWallets()
}