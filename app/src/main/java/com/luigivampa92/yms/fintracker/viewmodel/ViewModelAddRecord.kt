package com.luigivampa92.yms.fintracker.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.Transformations
import android.content.Context
import android.os.PersistableBundle
import androidx.work.*
import java.lang.Double.parseDouble
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet
import com.luigivampa92.yms.fintracker.scheduler.RecordWorker
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.util.concurrent.TimeUnit

class ViewModelAddRecord(application: Application) : AndroidViewModel(application) {

    private val mApplication = application

    fun addRecord(record: Record) {
        if (!record.repeatable && record.pending_time == 0) {
            addInstantRecord(record)
        } else if (record.pending_time != 0 && record.repeatable) {
            addRepeatingPendingRecord(record)
        } else {
            addPendingRecord(record)
        }
    }

    private fun addInstantRecord(record: Record) {
        launch {
            FinanceTrackerDatabase.getInstance(mApplication)?.recordsDao()?.addRecord(record)
        }
        updateWallet(record)
    }


    private fun addPendingRecord(record: Record) {
        val compressionWork = OneTimeWorkRequestBuilder<RecordWorker>()
                .setInitialDelay(16, TimeUnit.MINUTES)
                .setInputData(createWorkerArguments(record))
                .build()
        WorkManager.getInstance().enqueue(compressionWork)
    }


    private fun addRepeatingPendingRecord(record: Record) {
        val recordWork = PeriodicWorkRequestBuilder<RecordWorker>(16, TimeUnit.MINUTES)
                .setInputData(createWorkerArguments(record))
                .build()

        WorkManager.getInstance().enqueue(recordWork)
    }

    fun updateWallet(record: Record) {
        launch {
            val database = FinanceTrackerDatabase.getInstance(mApplication)
            val walletBalance = FinanceTrackerDatabase.getInstance(mApplication)?.walletsDao()
                    ?.getWalletObject(record.wallet_id)?.balance?.plus(
                    CurrencyConverter.convertCurrency(record.currency, record.amount, "USD")
            ) ?: 0.0
            database?.walletsDao()?.updateWalletBalance(record.wallet_id, walletBalance)
        }
    }


    private fun createWorkerArguments(record: Record): Data {
        return mapOf(Constants.RECORD_NAME to record.name,
                Constants.RECORD_CATEGORY to record.category,
                Constants.RECORD_INCOME to record.income,
                Constants.RECORD_AMOUNT to record.amount,
                Constants.RECORD_CURRENCY to record.currency,
                Constants.RECORD_WALET_ID to record.wallet_id,
                Constants.RECORD_DATE to record.date,
                Constants.RECORD_PENDING_TIME to record.pending_time,
                Constants.RECORD_REPEATABLE to record.repeatable)
                .toWorkData()
    }
}