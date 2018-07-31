package com.luigivampa92.yms.fintracker.data.network.model

import com.google.gson.annotations.SerializedName

data class AvailableSymbolsModel (
    @SerializedName("success") val success: Boolean,
    @SerializedName("symbols") val rates: Map<String,String>
)