package com.luigivampa92.yms.fintracker.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.data.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideExchangeRatesInterceptor() = ExchangeRatesInterceptor()

    @Singleton
    @Provides
    fun provideRetrofitLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: ExchangeRatesInterceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(loggingInterceptor)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build()

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateAdapter())
            .create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient) = Retrofit.Builder()
            .baseUrl(Constants.exchangeRateBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideElpassApi(retrofit: Retrofit) = retrofit.create(ExchangeRatesApi::class.java)

    @Singleton
    @Provides
    fun provideErrorMapper(retrofit: Retrofit): NetworkErrorMapper = NetworkErrorMapperImpl(retrofit)

    @Singleton
    @Provides
    fun provideExchangeRatesNetworkSource(api: ExchangeRatesApi) = ExchangeRatesNetworkSource(api)
}