package com.luigivampa92.yms.fintracker.view.fragments

import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Template
import com.luigivampa92.yms.fintracker.model.repositories.TemplatesRepository
import com.luigivampa92.yms.fintracker.utils.createId
import com.luigivampa92.yms.fintracker.utils.getTextFromView
import com.luigivampa92.yms.fintracker.utils.hasText
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelTemplates
import com.luigivampa92.yms.fintracker.viewmodel.factory.viewModelFactory
import kotlinx.android.synthetic.main.activity_add_record.*
import kotlinx.android.synthetic.main.fragment_template.*
import kotlinx.android.synthetic.main.fragment_templates.*
import java.util.*

class FragmentAddTemplate: Fragment(){

    lateinit var viewModel: ViewModelTemplates

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this,
                viewModelFactory { ViewModelTemplates(TemplatesRepository(FinanceTrackerDatabase.getInstance(activity!!.application)!!)) }).get(ViewModelTemplates::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_template, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
    }

    private fun initComponents(){
        currency_fragment_template.adapter = ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_dropdown_item, this.resources.getStringArray(R.array.currencies)
        )
        category_fragment_template.setAdapter(ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_dropdown_item, this.resources.getStringArray(R.array.expenditure_categories)
        ))
    }

    private fun initComponentsListeners(){

        val categoriesOptions = arrayListOf<String>()
        income_fragment_template.setOnCheckedChangeListener { buttonView, isChecked ->
            categoriesOptions.clear()
            if (isChecked) {
                categoriesOptions.addAll(this.resources.getStringArray(R.array.income_categories))
            } else {
                categoriesOptions.addAll(this.resources.getStringArray(R.array.expenditure_categories))
            }
            category_fragment_template.setAdapter(ArrayAdapter<String>(
                    context, android.R.layout.simple_spinner_dropdown_item, categoriesOptions
            ))
        }


        add_fragment_template.setOnClickListener { _ ->
            if (hasText(name_fragment_template, category_fragment_template,
                            amount_fragment_template)) {

                val name = getTextFromView(name_fragment_template)
                val category = getTextFromView(category_fragment_template)
                val income = income_fragment_template.isChecked
                var amount = getTextFromView(amount_fragment_template).toDouble()
                val currency = currency_fragment_template.selectedItem.toString()
                val repeatable = repeat_fragment_template.isChecked

                var pendingTime = 0
                if (hasText(pending_time_fragment_template)) {
                    pendingTime = getTextFromView(pending_time_fragment_template).toInt()
                }
                if (!income) amount = -amount


                val template = Template(0, name, category, income, amount, currency,
                        pendingTime, repeatable)

                viewModel.addTemplate(template)
                fragmentManager?.popBackStack()
            }
        }
    }

}