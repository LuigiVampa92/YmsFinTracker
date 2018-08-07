package com.luigivampa92.yms.fintracker.scheduler

import android.content.Context
import androidx.work.Worker
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.utils.createId
import kotlinx.coroutines.experimental.launch

class RecordWorker : Worker() {
    override fun doWork(): Result {
        val record = Record(
                createId(),
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


        launch {
            FinanceTrackerDatabase.getInstance(applicationContext)?.recordsDao()?.addRecord(record)
        }

        launch {
            val database = FinanceTrackerDatabase.getInstance(applicationContext)
            val walletBalance = FinanceTrackerDatabase.getInstance(applicationContext)?.walletsDao()
                    ?.getWalletObject(record.wallet_id)?.balance?.plus(
                    CurrencyConverter.convertCurrency(record.currency, record.amount, "USD")
            ) ?: 0.0
            database?.walletsDao()?.updateWalletBalance(record.wallet_id, walletBalance)
        }


        return Result.SUCCESS
    }

}