package com.luigivampa92.yms.fintracker

import android.app.Application
import android.content.Context
import com.luigivampa92.yms.fintracker.utils.fetchCurrenciesFromAssets
import kotlinx.coroutines.experimental.launch

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initFirstLaunchData()
    }

    private fun initFirstLaunchData() {
        val app = this
        val sf = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        if (sf.getString(Constants.CURRENT_WALLET_ID, null) == null) {
            launch {
                fetchCurrenciesFromAssets(app)
            }
        }
    }
}