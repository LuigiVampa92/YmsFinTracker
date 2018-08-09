package com.luigivampa92.yms.fintracker.calculations

import com.luigivampa92.yms.fintracker.model.Record

object DataGrinder{

    fun grindDataForPieChart(records: List<Record>, categories: Array<String>?): HashMap<String, Double>{
        val recordsHash: HashMap<String, Double> = hashMapOf()
//        records.forEach {
//            if(records.get)
//        }
        return recordsHash
    }

    fun getSumForPieChart(records: List<Record>): Double{
        var sum = 0.0
        records.forEach {
            sum += CurrencyConverter.convertCurrency(it.currency, it.amount, "USD")
        }
        return sum
    }
}