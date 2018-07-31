package com.luigivampa92.yms.fintracker.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ExchangeRatesModel (
        @SerializedName("success") val success: Boolean,
        @SerializedName("timestamp") val timestamp: Long,
        @SerializedName("base") val base: String,
        @SerializedName("date") val date: Date,
        @SerializedName("rates") val rates: Map<String,Double>
)