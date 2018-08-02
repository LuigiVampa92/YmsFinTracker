package com.luigivampa92.yms.fintracker.view.activities

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.luigivampa92.yms.fintracker.R
import kotlinx.android.synthetic.main.activity_add_record.*
import java.text.SimpleDateFormat
import java.util.*

class ActivityAddRecord : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record)

        initComponents()
        initComponentsListeners()
    }

    private fun initComponents() {
        currency_activity_add_record.adapter = ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, this.resources.getStringArray(R.array.currencies)
        )
        category_activity_add_record.setAdapter(ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, this.resources.getStringArray(R.array.expenditure_categories)
        ))
    }

    private fun initComponentsListeners() {

        val categoriesOptions = arrayListOf<String>()
        expense_activity_add_record.setOnCheckedChangeListener { buttonView, isChecked ->
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

        done_activity_add_record.setOnClickListener {
            //Pass data to db
            finish()
        }

        date_activity_add_record.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(this,
                    R.style.MateriaCalendar,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        date_activity_add_record.text = this.resources.getString(R.string.formatted_date, dayOfMonth, month, year)
                    },
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH))
            datePickerDialog.show()
        }

        pending_date_activity_add_record.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(this,
                    R.style.MateriaCalendar,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        pending_date_activity_add_record.text = this.resources.getString(R.string.formatted_date, dayOfMonth, month, year)
                    },
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH))
            datePickerDialog.show()
        }
    }
}
