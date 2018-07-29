package com.luigivampa92.yms.fintracker.data.network.error

import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.getString

class NoNetworkError : NetworkError(getString(R.string.text_error_no_network))