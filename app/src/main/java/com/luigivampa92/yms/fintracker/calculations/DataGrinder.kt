package com.luigivampa92.yms.fintracker.calculations

import com.luigivampa92.yms.fintracker.model.Category
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.utils.convertStringToDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap

object DataGrinder {

    //Пока что хардкод
    fun splitCategories(records: List<Record>): HashMap<String, Double> {
        val response = HashMap<String, Double>()
        records.forEach {
            if (response[it.category] == null) {
                response[it.category] = CurrencyConverter.convertCurrency(it.currency, it.amount, "USD")
            } else {
                response[it.category]?.plus(CurrencyConverter.convertCurrency(it.currency, it.amount, "USD"))
            }
        }
        return response
    }

    fun filterByDates(records: List<Record>, leftDate: Date, rightDate: Date): MutableList<Record> {
        val response = mutableListOf<Record>()
        records.forEach {
            if (convertStringToDate(it.date) > leftDate &&
                    convertStringToDate(it.date) < rightDate) {
                response.add(it)
            }
        }
        return response
    }

    fun getSumForPieChart(records: List<Record>): Double {
        var sum = 0.0
        records.forEach {
            sum += Math.abs(CurrencyConverter.convertCurrency(it.currency, it.amount, "USD"))
        }
        return sum
    }

}