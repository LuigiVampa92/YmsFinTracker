package com.luigivampa92.yms.fintracker.viewmodel

import android.arch.lifecycle.*
import com.luigivampa92.yms.fintracker.model.repositories.WalletsRepository

class ViewModelWallets(repository: WalletsRepository) : ViewModel() {

    private val mWalletsRepository = repository

    val wallets = mWalletsRepository.getWallets()
}