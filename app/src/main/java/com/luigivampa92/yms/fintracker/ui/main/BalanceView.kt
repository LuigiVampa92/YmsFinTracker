package com.luigivampa92.yms.fintracker.ui.main

import com.arellomobile.mvp.MvpView

interface BalanceView : MvpView {
    fun showBalanceRur(value: String)
    fun showBalanceUsd(value: String)
}