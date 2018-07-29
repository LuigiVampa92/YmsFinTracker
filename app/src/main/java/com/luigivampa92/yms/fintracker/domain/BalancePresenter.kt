package com.luigivampa92.yms.fintracker.domain

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.luigivampa92.yms.finops.RecordCalculator
import com.luigivampa92.yms.finops.model.*
import com.luigivampa92.yms.finops.model.Currency
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.di.scope.FragmentScope
import com.luigivampa92.yms.fintracker.getString
import com.luigivampa92.yms.fintracker.ui.main.BalanceView
import ru.terrakok.cicerone.Router
import java.util.*
import javax.inject.Inject

@FragmentScope
@InjectViewState
class BalancePresenter @Inject constructor(
        private val router: Router,
        private val exchangeRatesRepository: ExchangeRatesRepository,
        private val calculator: RecordCalculator
) : MvpPresenter<BalanceView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        exchangeRatesRepository.getExchangeRates().subscribe(
                {
                    it.forEach {
                        calculator.updateCurrencyExchangeRates(Pair(Currency.valueOf(it.from), Currency.valueOf(it.to)), it.value)
                    }

                    provideCurrentBalance()
                },
                {
                    router.showSystemMessage(getString(R.string.text_error_data))
                }
        )
    }

    fun provideCurrentBalance() {

        val accountCash = Account("Cash", Currency.RUB)
        val accountCard = Account("Card", Currency.RUB)
        val accountCardUsd = Account("CardUSD", Currency.USD)

        val categoryDebt = Category(OperationType.IN, "debt")
        val categoryPayment = Category(OperationType.IN, "payment")

        val categoryFood = Category(OperationType.OUT, "food")
        val categoryClothes = Category(OperationType.OUT,"clothes")
        val categoryBill = Category(OperationType.OUT, "bill")
        val categoryGasBill = Category(OperationType.OUT, "gas_bill", categoryBill)

        val record01 = Record(OperationType.IN, 1500.0, categoryPayment, Date(), accountCash)
        val record02 = Record(OperationType.OUT, 600.0, categoryGasBill, Date(), accountCash)

        val record03 = Record(OperationType.IN, 500.0, categoryDebt, Date(), accountCard)
        val record04 = Record(OperationType.OUT, 100.0, categoryClothes, Date(), accountCard)

        val record05 = Record(OperationType.IN, 50.0, categoryDebt, Date(), accountCardUsd)
        val record06 = Record(OperationType.OUT, 17.5, categoryFood, Date(), accountCardUsd)

        val balanceCash = calculator.sum(record01, record02)
        val balanceCard = calculator.sum(record03, record04)
        val balanceCardUsd = calculator.sum(record05, record06)

        viewState.showBalanceUsd("0")
        viewState.showBalanceRur("0")
    }

    fun addRecord() {
        router.showSystemMessage("Stub")
    }
}