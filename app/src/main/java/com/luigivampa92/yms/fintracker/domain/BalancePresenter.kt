package com.luigivampa92.yms.fintracker.domain

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.luigivampa92.yms.fintracker.model.*
import com.luigivampa92.yms.fintracker.model.Currency
import com.luigivampa92.yms.fintracker.ui.main.BalanceView
import java.util.*

@InjectViewState
class BalancePresenter : MvpPresenter<BalanceView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        provideCurrentBalance()
    }

    fun provideCurrentBalance() {

        val accountCash = Account("Cash", Currency.RUB)
        val accountCard = Account("Card", Currency.RUB)
        val accountCardUsd = Account("CardUSD", Currency.USD)

        val record01 = Record(1500.0, false, "salary", Date(), accountCash)
        val record02 = Record(600.0, true, "clothes", Date(), accountCash)

        val record03 = Record(500.0, false, "debt", Date(), accountCard)
        val record04 = Record(100.0, true, "food", Date(), accountCard)

        val record05 = Record(50.0, false, "salary", Date(), accountCardUsd)
        val record06 = Record(17.5, true, "food", Date(), accountCardUsd)

        viewState.showBalanceUsd("0")
        viewState.showBalanceRur("0")
    }
}