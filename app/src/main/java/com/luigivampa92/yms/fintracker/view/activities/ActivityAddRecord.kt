package com.luigivampa92.yms.fintracker.view.activities

import android.app.DatePickerDialog
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.arch.lifecycle.ViewModelProviders
import android.content.ComponentName
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import java.lang.Double.parseDouble
import android.widget.ArrayAdapter
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.utils.getTextFromView
import com.luigivampa92.yms.fintracker.utils.hasText
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.scheduler.JobSchedulerService
import com.luigivampa92.yms.fintracker.utils.createId
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelAddRecord
import kotlinx.android.synthetic.main.activity_add_record.*
import java.util.*

class ActivityAddRecord : AppCompatActivity() {

    private lateinit var mViewModel: ViewModelAddRecord

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record)

        initComponents()
        initComponentsListeners()
    }

    private fun initComponents() {

        mViewModel = ViewModelProviders.of(this).get(ViewModelAddRecord::class.java)
        currency_activity_add_record.adapter = ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, this.resources.getStringArray(R.array.currencies)
        )
        category_activity_add_record.setAdapter(ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, this.resources.getStringArray(R.array.expenditure_categories)
        ))
    }

    private fun initComponentsListeners() {

        val categoriesOptions = arrayListOf<String>()
        income_activity_add_record.setOnCheckedChangeListener { buttonView, isChecked ->
            categoriesOptions.clear()
            if (isChecked) {
                categoriesOptions.addAll(this.resources.getStringArray(R.array.income_categories))
            } else {
                categoriesOptions.addAll(this.resources.getStringArray(R.array.expenditure_categories))
            }
            category_activity_add_record.setAdapter(ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_dropdown_item, categoriesOptions
            ))
        }

        toolbar_activity_add_record.setNavigationOnClickListener {
            finish()
        }

        date_activity_add_record.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(this,
                    R.style.MateriaCalendar,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        date_label_activity_add_record.text = this.resources.getString(R.string.formatted_date, dayOfMonth, month, year)
                    },
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH))
            datePickerDialog.show()
        }


        done_activity_add_record.setOnClickListener { _ ->
            if (hasText(name_activity_add_record, category_activity_add_record,
                            amount_activity_add_record)) {
                val sf = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
                val name = getTextFromView(name_activity_add_record)
                val category = getTextFromView(category_activity_add_record)
                val income = income_activity_add_record.isChecked
                var amount = getTextFromView(amount_activity_add_record).toDouble()
                val currency = currency_activity_add_record.selectedItem.toString()
                val walletId = sf.getString(Constants.CURRENT_WALLET_ID, "DEFAULT")
                val date = getTextFromView(date_activity_add_record)
                val pendingDate = getTextFromView(pending_activity_add_record)

                if (!income) amount = -amount

                val record = Record(0,
                        name,
                        category,
                        income,
                        amount,
                        currency,
                        walletId,
                        date,
                        pendingDate)
                val walletBalance = parseDouble(sf.getString(Constants.CURRENT_WALLET_BALANCE, null)) +
                        CurrencyConverter.convertCurrency(currency, amount, "USD")

                val bundle = PersistableBundle()
                bundle.putString(Constants.RECORD_NAME, name)
                bundle.putString(Constants.RECORD_CATEGORY, category)
                bundle.putInt(Constants.RECORD_INCOME, if (income) 1 else 0)
                bundle.putDouble(Constants.RECORD_AMOUNT, amount)
                bundle.putString(Constants.RECORD_CURRENCY, currency)
                bundle.putString(Constants.RECORD_WALET_ID, walletId)
                bundle.putString(Constants.RECORD_DATE, date)
                bundle.putDouble(Constants.WALLET_BALANCE, walletBalance)

                if (hasText(pending_activity_add_record) && repeat_activity_add_record.isChecked) {
                    val service = ComponentName(this, JobSchedulerService::class.java)
                    val jobInfo = JobInfo.Builder(Constants.REPEATED_JOB_ID, service)
                            .setPeriodic(pending_activity_add_record.text.toString().toLong() * 864 * 100000)
                            .setPersisted(true)
                            .setExtras(bundle)
                            .build()
                    val scheduler = this.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
                    scheduler.schedule(jobInfo)
                } else if (hasText(pending_activity_add_record) && !repeat_activity_add_record.isChecked) {
                    val service = ComponentName(this, JobSchedulerService::class.java)
                    val jobInfo = JobInfo.Builder(Constants.JOB_ID, service)
                            .setMinimumLatency(getTextFromView(pending_activity_add_record).toLong() * 864 * 100000)
                            .setPersisted(true)
                            .setExtras(bundle)
                            .build()
                    val scheduler = this.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
                    scheduler.schedule(jobInfo)
                } else {
                    mViewModel.addRecord(record)
                    //Перед обновлением баланса выбранного кошелька, переводим "ценность" записи в $
                    mViewModel.updateWallet(walletId, walletBalance)
                    sf.edit().putString(Constants.CURRENT_WALLET_BALANCE, walletBalance.toString()).apply()
                }

                finish()
            }
        }
    }
}
