package com.luigivampa92.yms.fintracker.data

import okhttp3.ResponseBody

interface NetworkErrorMapper {
    fun map(error: Throwable): NetworkError
    fun map(body: ResponseBody?): NetworkError
}