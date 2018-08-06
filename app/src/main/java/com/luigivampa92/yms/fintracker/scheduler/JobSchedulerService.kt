package com.luigivampa92.yms.fintracker.scheduler

import android.app.job.JobParameters
import android.app.job.JobService
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import kotlinx.coroutines.experimental.launch

class JobSchedulerService : JobService() {

    override fun onStopJob(p0: JobParameters?): Boolean {
        val record = Record(
                0,
                p0?.extras!!.getString(Constants.RECORD_NAME),
                p0.extras!!.getString(Constants.RECORD_CATEGORY),
                p0.extras!!.getString(Constants.RECORD_INCOME)?.toBoolean()!!,
                p0.extras!!.getString(Constants.RECORD_AMOUNT).toDouble(),
                p0.extras!!.getString(Constants.RECORD_CURRENCY),
                p0.extras!!.getString(Constants.RECORD_WALET_ID),
                p0.extras!!.getString(Constants.RECORD_DATE),
                p0.extras!!.getString(Constants.RECORD_PENDING_TIME).toLong()
        )
        val balance = p0.extras!!.getString(Constants.WALLET_BALANCE).toDouble() - p0.extras!!.getString(Constants.RECORD_AMOUNT).toDouble()
        val context = this
        launch {
            FinanceTrackerDatabase.getInstance(context)?.recordsDao()?.addRecord(record)
        }
        launch {
            FinanceTrackerDatabase.getInstance(context)?.walletsDao()?.updateWalletBalance(p0.extras!!.getString(Constants.RECORD_WALET_ID),
                    balance)
        }
        return true
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        return false
    }

}