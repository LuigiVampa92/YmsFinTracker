package com.luigivampa92.yms.fintracker.scheduler

import android.content.Context
import androidx.work.Worker
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import kotlinx.coroutines.experimental.launch

class RecordWorker : Worker() {
    override fun doWork(): Result {
        val record = Record(
                0,
                inputData.getString(Constants.RECORD_NAME) ?: "Pedning",
                inputData.getString(Constants.RECORD_CATEGORY) ?: "Pending Category",
                inputData.getBoolean(Constants.RECORD_INCOME, false),
                inputData.getDouble(Constants.RECORD_AMOUNT, 0.0),
                inputData.getString(Constants.RECORD_CURRENCY) ?: "USD",
                inputData.getString(Constants.RECORD_WALET_ID) ?: "228",
                inputData.getString(Constants.RECORD_DATE) ?: "2018",
                inputData.getString(Constants.RECORD_PENDING_TIME)?.toInt() ?: 0,
                inputData.getBoolean(Constants.RECORD_REPEATABLE, false)
        )

        val sf = applicationContext.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val walletBalance = sf.getString(Constants.WALLET_BALANCE, "0").toDouble() + CurrencyConverter.convertCurrency(record.currency, record.amount, "USD")
        val walletId = record.wallet_id

        launch {
            FinanceTrackerDatabase.getInstance(applicationContext)?.recordsDao()?.addRecord(record)
        }

        launch {
            FinanceTrackerDatabase.getInstance(applicationContext)?.walletsDao()?.updateWalletBalance(walletId, walletBalance)
        }

        return Result.SUCCESS
    }

}