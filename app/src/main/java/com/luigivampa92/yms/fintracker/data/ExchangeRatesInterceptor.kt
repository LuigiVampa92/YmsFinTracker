package com.luigivampa92.yms.fintracker.data

import okhttp3.Interceptor
import okhttp3.Response

class ExchangeRatesInterceptor : Interceptor {

    private var accessKey = ""

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("access_key", accessKey).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    fun setAccessKey(accessKey: String) {
        this.accessKey = accessKey
    }
}