package com.luigivampa92.yms.fintracker

import android.app.Application
import android.content.Context
import com.luigivampa92.yms.fintracker.utils.fetchCategoriesFromAssets
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

        if (sf.getString(Constants.FIRST_LAUNCH, null) == null) {
            launch {
                fetchCategoriesFromAssets(app)
                fetchCurrenciesFromAssets(app)
            }
            sf.edit().putString(Constants.FIRST_LAUNCH, Constants.FIRST_LAUNCH).apply()
            getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE).edit().putString(Constants.SECONDARY_CURRENCY, "RUB").apply()
        }
    }
}