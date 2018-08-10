package com.luigivampa92.yms.fintracker

import com.luigivampa92.yms.fintracker.model.Currency
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.utils.createId

abstract class MainUnitTest {
    val walletId = createId()
    val record1 = Record("", "Burger", "Еда",
            false, 126.0, "RUB", walletId, "05.08.2018",
            0)
    val record2 = Record("1", "Hot-Dog", "Еда",
            false, 126.0, "RUB", walletId, "05.08.2018",
            2)
    val record3 = Record("2", "Apple", "Еда",
            false, 126.0, "RUB", walletId, "05.08.2018",
            0)
    val record4 = Record("3", "Salary", "Зарплата",
            true, 150.0, "USD", walletId, "05.08.2018",
            0)
    val record5 = Record("3", "Vzyatka", "Зарплата",
            true, 250.0, "USD", walletId, "05.08.2018",
            0)

    val records = arrayListOf(record1, record2, record3, record4, record5)

    val rub = Currency(0L, "RUB", 63.0)
    val usd = Currency(0L, "USD", 1.0)

}