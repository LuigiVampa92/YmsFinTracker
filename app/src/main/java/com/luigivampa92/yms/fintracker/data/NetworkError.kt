package com.luigivampa92.yms.fintracker.data

import com.google.gson.annotations.SerializedName
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.getString

open class NetworkError: Throwable {

    @SerializedName("message") override var message: String? = ""
    @SerializedName("code") var code: Int = 0

    constructor(): super(getString(R.string.text_error_network))

    constructor(message: String): super() {
        this.message = message
    }
}