package com.luigivampa92.yms.fintracker.data.network.error

import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Retrofit
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class NetworkErrorMapperImpl @Inject constructor(
        private val retrofit: Retrofit
): NetworkErrorMapper {

    override fun map(error: Throwable) =
            when {
                error is NoNetworkError -> error
                error.cause is SocketTimeoutException || error.cause is UnknownHostException -> NoNetworkError()
                error is HttpException -> map(error.response().errorBody())
                else -> NetworkError()
            }

    override fun map(body: ResponseBody?) =
            try {
                if (body != null) {
                    val converter = retrofit.responseBodyConverter<NetworkError>(NetworkError::class.java, arrayOfNulls<Annotation>(0))
                    converter.convert(body)
                }
                else {
                    NetworkError()
                }
            } catch (ignore: Exception) {
                NetworkError()
            }
}