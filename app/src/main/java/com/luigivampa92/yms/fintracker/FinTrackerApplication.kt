package com.luigivampa92.yms.fintracker

import android.app.Application

class FinTrackerApplication : Application() {

    companion object {
        @JvmStatic lateinit var INSTANCE: FinTrackerApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}