package com.luigivampa92.yms.fintracker.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.constraint.Constraints
import android.util.Log
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.calculations.Currencies
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Category
import com.luigivampa92.yms.fintracker.model.Currency
import okhttp3.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

fun fetchCurrencies(application: Application) {
    val API_KEY = "3be78471808493901286fa1c7e22266d"
    val database = FinanceTrackerDatabase.getInstance(application)
    val client = OkHttpClient()
    val url = "http://www.apilayer.net/api/live?access_key=$API_KEY&format=1"
    val request = Request.Builder().url(url).build()
    try {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                e?.printStackTrace()
            }

            override fun onResponse(call: Call?, response: Response?) {
                response?.body()?.string()?.let {
                    val data = JSONObject(it).getJSONObject("quotes")
                    val keys = data.names()
                    for (i in 0 until keys.length()) {
                        val key = keys.get(i).toString()
                        val value = data.getString(key)
                        Currencies.currencies.add(Currency(0, key.substring(3), value.toDouble()))
                        database?.currenciesDao()?.addCurrency(Currency(0, key.substring(3), value.toDouble()))
                    }

                }
            }

        })
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


fun fetchCurrenciesFromAssets(application: Application) {
    val database = FinanceTrackerDatabase.getInstance(application)
    var jsonText: String? = ""
    try {
        val inputStream = application.resources.openRawResource(R.raw.currencies)
        inputStream.bufferedReader().use {
            jsonText = it.readText()
        }
    } catch (e: Exception) {
        Log.d(Constraints.TAG, e.toString())
    }
    val data = JSONObject(jsonText).getJSONObject("quotes")
    val keys = data.names()
    for (i in 0 until keys.length()) {
        val key = keys.get(i).toString()
        val value = data.getString(key)
        Currencies.currencies.add(Currency(0, key.substring(3), value.toDouble()))
        database?.currenciesDao()?.addCurrency(Currency(0, key.substring(3), value.toDouble()))
    }
}


fun fetchCategoriesFromAssets(application: Application) {
    val incomeCategories = application.applicationContext.resources.getStringArray(R.array.income_categories)
    val expendiTureCategories = application.applicationContext.resources.getStringArray(R.array.expenditure_categories)
    val database = FinanceTrackerDatabase.getInstance(application)
    incomeCategories.forEach {
        database?.categoriesDao()?.addCategory(Category(0, it, true))
    }
    expendiTureCategories.forEach {
        database?.categoriesDao()?.addCategory(Category(0, it, false))
    }
}

