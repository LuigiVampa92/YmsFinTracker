package com.luigivampa92.yms.fintracker.domain

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.luigivampa92.yms.finops.RecordCalculator
import com.luigivampa92.yms.finops.model.Currency
import com.luigivampa92.yms.finops.model.OperationType
import com.luigivampa92.yms.finops.model.Record
import com.luigivampa92.yms.fintracker.di.scope.FragmentScope
import com.luigivampa92.yms.fintracker.formatAsMoney
import com.luigivampa92.yms.fintracker.ui.main.BalanceView
import javax.inject.Inject

@FragmentScope
@InjectViewState
class BalancePresenter @Inject constructor(
        private val exchangeRatesRepository: ExchangeRatesRepository,
        private val calculator: RecordCalculator
) : MvpPresenter<BalanceView>() {

    init {

        // just a stub for now
        val usdRurExchangeRate = 60.0
        val rurUsdExchangeRate = 1.0 / usdRurExchangeRate

        calculator.updateCurrencyExchangeRates(Pair(Currency.USD, Currency.RUR), usdRurExchangeRate)
        calculator.updateCurrencyExchangeRates(Pair(Currency.RUR, Currency.USD), rurUsdExchangeRate)
    }

    fun provideCurrentBalance() {

        val record01 = Record(OperationType.REVENUE, 1500, Currency.USD)
        val record02 = Record(OperationType.EXPENSE, 600, Currency.USD)
        val record03 = Record(OperationType.EXPENSE, 1234, Currency.USD)
        val record04 = Record(OperationType.REVENUE, 4500, Currency.USD)

        val balanceUsd = calculator.sum(record01, record02, record03, record04)
        val balanceRur = calculator.convert(balanceUsd, Currency.RUR)

        viewState.showBalanceUsd(balanceUsd.amount.formatAsMoney())
        viewState.showBalanceRur(balanceRur.amount.formatAsMoney())
    }

    fun testQuery() {

        exchangeRatesRepository.getExchangeRates().subscribe(
                {
                    val a = "a"
                },
                {
                    val a = "a"
                }
        )

        val a = "a"

    }
}