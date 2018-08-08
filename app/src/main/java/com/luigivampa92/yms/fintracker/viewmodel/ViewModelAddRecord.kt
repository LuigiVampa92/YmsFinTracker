package com.luigivampa92.yms.fintracker.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import androidx.work.*
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.repositories.Repository
import com.luigivampa92.yms.fintracker.workers.RecordWorker
import kotlinx.coroutines.experimental.launch
import java.util.concurrent.TimeUnit

class ViewModelAddRecord(repository: Repository) : ViewModel() {

    //По идее, дефолтная валюта USD, поэтому перед любой транзакцией необходимо сделать перевод в доллары
    private val mRepository = repository

    fun addRecord(record: Record) {
        if (!record.repeatable && record.pending_time == 0) {
            addInstantRecord(record)
        } else if (record.pending_time != 0 && record.repeatable) {
            addRepeatingPendingRecord(record)
        } else {
            addPendingRecord(record)
        }
    }

    fun editRecord(record: Record, oldRecord: Record){
        record.amount = CurrencyConverter.convertCurrency(record.currency, record.amount)
        oldRecord.amount = CurrencyConverter.convertCurrency(oldRecord.currency, oldRecord.amount)
        mRepository.editRecord(record, oldRecord)
    }

    private fun addInstantRecord(record: Record) {
        record.amount = CurrencyConverter.convertCurrency(record.currency, record.amount)
        mRepository.addRecord(record)
    }


    private fun addPendingRecord(record: Record) {
        record.amount = CurrencyConverter.convertCurrency(record.currency, record.amount)
        val compressionWork = OneTimeWorkRequestBuilder<RecordWorker>()
                .setInitialDelay(record.pending_time.toLong() * 7, TimeUnit.DAYS)
                .setInputData(createWorkerArguments(record))
                .build()
        WorkManager.getInstance().enqueue(compressionWork)
    }


    private fun addRepeatingPendingRecord(record: Record) {
        record.amount = CurrencyConverter.convertCurrency(record.currency, record.amount)
        val recordWork = PeriodicWorkRequestBuilder<RecordWorker>(record.pending_time.toLong() * 7, TimeUnit.DAYS)
                .setInputData(createWorkerArguments(record))
                .build()

        WorkManager.getInstance().enqueue(recordWork)
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