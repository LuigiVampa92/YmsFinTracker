package com.luigivampa92.yms.fintracker

import com.luigivampa92.yms.fintracker.data.ExchangeRatesModelToDomainMapper
import com.luigivampa92.yms.fintracker.data.network.model.ExchangeRatesModel
import org.junit.Assert
import org.junit.Test

import java.util.*

class ExchangeRatesModelToDomainMapperTest {

    @Test
    fun testRatesAreCorrectlyCalculatedAndMapped() {
        val model = ExchangeRatesModel(true, Date().time, "EUR", Date(), mapOf<String, Double>(
                "USD" to 1.167256,
                "EUR" to 1.0,
                "RUB" to 73.285018
        ))

        val mapper = ExchangeRatesModelToDomainMapper()
        val result = mapper.map(model)

        Assert.assertEquals(result.size, 9)

        val DELTA = 0.1

        val EurEur = result.firstOrNull { it.from == "EUR" && it.to == "EUR" }
        val EurUsd = result.firstOrNull { it.from == "EUR" && it.to == "USD" }
        val EurRub = result.firstOrNull { it.from == "EUR" && it.to == "RUB" }
        val UsdEur = result.firstOrNull { it.from == "USD" && it.to == "EUR" }
        val UsdUsd = result.firstOrNull { it.from == "USD" && it.to == "USD" }
        val UsdRub = result.firstOrNull { it.from == "USD" && it.to == "RUB" }
        val RubEur = result.firstOrNull { it.from == "RUB" && it.to == "EUR" }
        val RubUsd = result.firstOrNull { it.from == "RUB" && it.to == "USD" }
        val RubRub = result.firstOrNull { it.from == "RUB" && it.to == "RUB" }

        Assert.assertEquals(EurEur!!.value, 1.0, DELTA)
        Assert.assertEquals(EurUsd!!.value, 1.167256, DELTA)
        Assert.assertEquals(EurRub!!.value, 73.285018, DELTA)
        Assert.assertEquals(UsdEur!!.value, 0.85671, DELTA)
        Assert.assertEquals(UsdUsd!!.value, 1.0, DELTA)
        Assert.assertEquals(UsdRub!!.value, 62.784, DELTA)
        Assert.assertEquals(RubEur!!.value, 0.0136453538, DELTA)
        Assert.assertEquals(RubUsd!!.value, 0.0159276211, DELTA)
        Assert.assertEquals(RubRub!!.value, 1.0, DELTA)
    }
}
