package com.luigivampa92.yms.fintracker.view.activities

import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.utils.getTextFromView
import com.luigivampa92.yms.fintracker.utils.hasText
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelAddRecord
import kotlinx.android.synthetic.main.activity_add_record.*
import java.util.*
import java.util.concurrent.TimeUnit

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
                val repeatable = repeat_activity_add_record.isChecked

                var pendingTime = 0L
                if(hasText(pending_time_activity_add_record)){
                    pendingTime = TimeUnit.DAYS.toMillis(getTextFromView(pending_time_activity_add_record).toLong())
                }
                if (!income) amount = -amount

                val record = Record(0, name, category, income, amount, currency,
                        walletId, date, pendingTime, repeatable)

                mViewModel.addRecord(record)
                finish()
            }
        }
    }
}
