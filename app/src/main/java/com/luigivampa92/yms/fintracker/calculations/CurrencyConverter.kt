package com.luigivampa92.yms.fintracker.calculations

class CurrencyConverter {


    companion object {
        val USD = 62.5
        fun convertToRoubles(amount: Double?): Double {
            if(amount != null){
                return amount * USD
            }
            return USD
        }
    }
}