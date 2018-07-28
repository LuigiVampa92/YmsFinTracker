package com.luigivampa92.yms.fintracker.data

import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.getString

class NoNetworkError : NetworkError(getString(R.string.text_info_about_app_author))