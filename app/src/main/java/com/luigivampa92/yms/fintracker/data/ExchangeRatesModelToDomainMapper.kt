package com.luigivampa92.yms.fintracker.data

import com.luigivampa92.yms.fintracker.data.network.model.ExchangeRatesModel
import com.luigivampa92.yms.fintracker.domain.ExchangeRate
import java.util.ArrayList
import javax.inject.Inject

class ExchangeRatesModelToDomainMapper @Inject constructor () : Mapper<ExchangeRatesModel, List<ExchangeRate>> {

    override fun map(value: ExchangeRatesModel): List<ExchangeRate> {
        val base = value.base
        val rates = value.rates

        val list: ArrayList<ExchangeRate> = ArrayList()
        rates.keys.forEach { outer ->
            val localBaseRate: Double = rates[base]!! / rates[outer]!!
            rates.keys.forEach { inner ->
                list.add(ExchangeRate(outer, inner, localBaseRate * rates[inner]!!))
            }
        }

        return list
    }
}