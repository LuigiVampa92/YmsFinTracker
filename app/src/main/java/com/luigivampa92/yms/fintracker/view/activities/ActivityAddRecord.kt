package com.luigivampa92.yms.fintracker.view.activities

import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Category
import com.luigivampa92.yms.fintracker.utils.getTextFromView
import com.luigivampa92.yms.fintracker.utils.hasText
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Template
import com.luigivampa92.yms.fintracker.model.repositories.Repository
import com.luigivampa92.yms.fintracker.utils.createId
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelAddRecord
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelRecordsWallet
import com.luigivampa92.yms.fintracker.viewmodel.factory.viewModelFactory
import kotlinx.android.synthetic.main.abc_list_menu_item_checkbox.view.*
import kotlinx.android.synthetic.main.activity_add_record.*
import java.util.*

open class ActivityAddRecord : AppCompatActivity() {

    lateinit var viewModel: ViewModelAddRecord
    var mCategories: MutableList<Category> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record)

        if (intent.getParcelableExtra<Template>(Constants.TEMPLATE) != null) {
            initTemplate(intent.getParcelableExtra<Template>(Constants.TEMPLATE))
        }

        initComponents()
        initComponentsListeners()
        initComponentsObservers()
    }

    private fun initComponents() {
        val database = FinanceTrackerDatabase.getInstance(this.application)
        viewModel = ViewModelProviders.of(this,
                viewModelFactory { ViewModelAddRecord(Repository(database!!)) }).get(ViewModelAddRecord::class.java)

        currency_activity_add_record.adapter = ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, this.resources.getStringArray(R.array.currencies)
        )
        category_activity_add_record.adapter = ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, this.resources.getStringArray(R.array.expenditure_categories)
        )
    }

    private fun initComponentsListeners() {

        val categoriesOptions = arrayListOf<String>()
        income_activity_add_record.setOnCheckedChangeListener { buttonView, isChecked ->
            categoriesOptions.clear()

            mCategories.forEach {
                if (it.income == isChecked) categoriesOptions.add(it.name)
            }

            category_activity_add_record.adapter = ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_dropdown_item, categoriesOptions
            )
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
            if (hasText(name_activity_add_record, amount_activity_add_record)) {

                val sf = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
                val id = createId()
                val name = getTextFromView(name_activity_add_record)
                val category = category_activity_add_record.selectedItem.toString()
                val income = income_activity_add_record.isChecked
                var amount = getTextFromView(amount_activity_add_record).toDouble()
                val currency = currency_activity_add_record.selectedItem.toString()
                val walletId = sf.getString(Constants.CURRENT_WALLET_ID, "DEFAULT")
                val date = getTextFromView(date_label_activity_add_record)
                val repeatable = repeat_activity_add_record.isChecked

                var pendingTime = 0
                if (hasText(pending_time_activity_add_record)) {
                    pendingTime = getTextFromView(pending_time_activity_add_record).toInt()
                }

                //Пока таблицу для категорий не добавил
                if (!income) {
                    amount = -amount
                }


                val record = Record(id, name, category, income, amount, currency,
                        walletId, date, pendingTime, repeatable)


                makeTransaction(record)
                finish()
            }
        }
    }

    fun initComponentsObservers() {
        viewModel.getCategories().observe(this, android.arch.lifecycle.Observer { it ->
            mCategories.clear()
            it?.forEach {
                mCategories.add(it)
            }
        })
    }

    //Переопределим его в потомке, чтобы лишний раз код не писать
    open fun makeTransaction(record: Record) {
        viewModel.addRecord(record)
    }

    //Метод для вызывание активити с темплейтом
    fun initTemplate(template: Template) {

        val array = resources.getStringArray(R.array.currencies)

        name_activity_add_record.setText(template.name)
        income_activity_add_record.isChecked = template.income
        amount_activity_add_record.setText(template.amount.toString())
        currency_activity_add_record.setSelection(array.indexOf(template.currency), true)
        pending_time_activity_add_record.setText(template.pending_time.toString())
        repeat_activity_add_record.isChecked = template.repeatable
    }
}
