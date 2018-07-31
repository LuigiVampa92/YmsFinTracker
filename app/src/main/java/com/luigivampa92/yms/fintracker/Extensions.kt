package com.luigivampa92.yms.fintracker

import android.support.annotation.StringRes

fun getString(@StringRes id: Int) = FinTrackerApplication.INSTANCE.getString(id)