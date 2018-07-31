package com.luigivampa92.yms.fintracker.data.network.error

import okhttp3.ResponseBody

interface NetworkErrorMapper {
    fun map(error: Throwable): NetworkError
    fun map(body: ResponseBody?): NetworkError
}