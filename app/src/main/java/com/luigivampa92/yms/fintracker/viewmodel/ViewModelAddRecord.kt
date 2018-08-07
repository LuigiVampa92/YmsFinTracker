package com.luigivampa92.yms.fintracker.viewmodel

import android.app.Application
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.arch.lifecycle.AndroidViewModel
import android.content.ComponentName
import android.content.Context
import android.os.PersistableBundle
import java.lang.Double.parseDouble
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.scheduler.JobSchedulerService
import com.luigivampa92.yms.fintracker.utils.getTextFromView
import kotlinx.android.synthetic.main.activity_add_record.*
import kotlinx.coroutines.experimental.launch
import java.util.concurrent.TimeUnit

class ViewModelAddRecord(application: Application) : AndroidViewModel(application) {

    private val mApplication = application

    fun addRecord(record: Record) {
        if(!record.repeatable && record.pending_time == 0L){
            addInstantRecord(record)
        }else if(record.pending_time != 0L && record.repeatable){
            addRepeatingPendingRecord(record)
        }else{
            addPendingRecord(record)
        }
    }

    private fun addInstantRecord(record: Record){
        launch {
            FinanceTrackerDatabase.getInstance(mApplication)?.recordsDao()?.addRecord(record)
        }
        updateWallet(record)
    }


    private fun addPendingRecord(record: Record) {
        val service = ComponentName(mApplication, JobSchedulerService::class.java)
        val jobInfo = JobInfo.Builder(Constants.JOB_ID, service)
                .setMinimumLatency(record.pending_time)
                .setPersisted(true)
                .setExtras(createPersistableBundle(record))
                .build()
        val scheduler = mApplication.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.schedule(jobInfo)
    }


    private fun addRepeatingPendingRecord(record: Record) {
        val service = ComponentName(mApplication, JobSchedulerService::class.java)
        val jobInfo = JobInfo.Builder(Constants.REPEATED_JOB_ID, service)
                .setPeriodic(record.pending_time)
                .setPersisted(true)
                .setExtras(createPersistableBundle(record))
                .build()
        val scheduler = mApplication.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.schedule(jobInfo)
    }

    private fun updateWallet(record: Record) {
        val sf = mApplication.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val walletId = record.wallet_id
        val walletBalance = parseDouble(sf.getString(Constants.CURRENT_WALLET_BALANCE, null)) +
                CurrencyConverter.convertCurrency(record.currency, record.amount, "USD")
        sf.edit().putString(Constants.CURRENT_WALLET_BALANCE, walletBalance.toString()).apply()
        launch {
            val database = FinanceTrackerDatabase.getInstance(mApplication)
            database?.walletsDao()?.updateWalletBalance(walletId, walletBalance)
        }
    }

    private fun createPersistableBundle(record: Record): PersistableBundle {
        val sf = mApplication.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val walletId = sf.getString(Constants.CURRENT_WALLET_ID, "DEFAULT")
        val walletBalance = parseDouble(sf.getString(Constants.CURRENT_WALLET_BALANCE, null)) +
                CurrencyConverter.convertCurrency(record.currency, record.amount, "USD")
        val bundle = PersistableBundle()
        bundle.putString(Constants.RECORD_NAME, record.name)
        bundle.putString(Constants.RECORD_CATEGORY, record.category)
        bundle.putInt(Constants.RECORD_INCOME, if (record.income) 1 else 0)
        bundle.putDouble(Constants.RECORD_AMOUNT, record.amount)
        bundle.putString(Constants.RECORD_CURRENCY, record.currency)
        bundle.putString(Constants.RECORD_WALET_ID, walletId)
        bundle.putString(Constants.RECORD_DATE, record.date)
        bundle.putDouble(Constants.WALLET_BALANCE, walletBalance)
        return bundle
    }
}