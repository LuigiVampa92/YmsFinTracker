package com.luigivampa92.yms.fintracker.utils

import com.luigivampa92.yms.fintracker.model.Currency
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

fun getCurrencies(): MutableList<Currency> {
    val client = OkHttpClient()
    val url = "http://www.apilayer.net/api/live?access_key=3be78471808493901286fa1c7e22266d&format=1"
    val request = Request.Builder().url(url).build()
    val currencies: MutableList<Currency> = mutableListOf()
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
                        currencies.add(Currency(0, key.substring(3), value.toDouble()))
                    }

                }
            }

        })
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return currencies
}