package com.luigivampa92.yms.fintracker

import com.luigivampa92.yms.fintracker.calculations.Currencies
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.model.Currency
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.utils.createId
import org.junit.Assert.assertArrayEquals

import org.junit.Test as test

class CurrencyConverterUnitTest : MainUnitTest() {


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

        assertArrayEquals(ratiosInDollars.toTypedArray(), listOf(2.0, 2.0, 2.0, 150.0, 250.0).toTypedArray())
        assertArrayEquals(ratiosInRoubles.toTypedArray(), listOf(126.0, 126.0, 126.0, 150.0 * 63.0, 250.0 * 63.0).toTypedArray())
    }
}