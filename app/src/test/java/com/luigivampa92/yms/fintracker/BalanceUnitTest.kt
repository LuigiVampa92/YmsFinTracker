package com.luigivampa92.yms.fintracker

import com.luigivampa92.yms.fintracker.calculations.Currencies
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.model.Currency
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet
import com.luigivampa92.yms.fintracker.utils.createId
import org.junit.Assert.assertArrayEquals

import org.junit.Test as test

class BalanceUnitTest {

    val walletId = createId()
    val wallet = Wallet(walletId, "Wallet", 250.0, "USD")
    val record1 = Record(0L, "Burger", "Food",
            false, 126.0, "RUB", walletId, "05.08.2018",
            null)
    val record2 = Record(1L, "Hot-Dog", "Food",
            false, 126.0, "RUB", walletId, "05.08.2018",
            null)
    val record3 = Record(2L, "Apple", "Food",
            false, 126.0, "RUB", walletId, "05.08.2018",
            null)
    val record4 = Record(3L, "Salary", "Salary",
            true, 150.0, "USD", walletId, "05.08.2018",
            null)
    val record5 = Record(4L, "Vzyatka", "Vzyatka",
            true, 250.0, "USD", walletId, "05.08.2018",
            null)
    val records = arrayListOf(record1, record2, record3, record4, record5)

    val rub = Currency(0L, "RUB", 63.0)
    val usd = Currency(0L, "USD", 1.0)

    @test
    fun converterUnitTest() {

        Currencies.currencies.add(rub)
        Currencies.currencies.add(usd)

        val ratiosInDollars = mutableListOf<Double>()
        records.forEach {
            ratiosInDollars.add(CurrencyConverter.convertCurrency(it.currency, it.amount, "USD"))
        }

        val ratiosInRoubles = mutableListOf<Double>()
        records.forEach {
            ratiosInRoubles.add(CurrencyConverter.convertCurrency(it.currency, it.amount, "RUB"))
        }

        //Перевод валют в доллары
        assertArrayEquals(ratiosInDollars.toTypedArray(), listOf(2.0, 2.0, 2.0, 150.0, 250.0).toTypedArray())
        assertArrayEquals(ratiosInRoubles.toTypedArray(), listOf(126.0, 126.0, 126.0, 150.0 * 63.0, 250.0 * 63.0).toTypedArray())
    }
}