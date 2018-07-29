package com.luigivampa92.yms.fintracker.data.network

import com.luigivampa92.yms.fintracker.data.network.model.AvailableSymbolsModel
import com.luigivampa92.yms.fintracker.data.network.model.ExchangeRatesModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ExchangeRatesApi {

    @GET("latest")
    fun getLatest(@QueryMap params: Map<String,String>) : Single<ExchangeRatesModel>

    @GET("symbols")
    fun getSymbols(): Single<AvailableSymbolsModel>
}