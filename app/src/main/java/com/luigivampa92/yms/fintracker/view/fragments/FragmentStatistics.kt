package com.luigivampa92.yms.fintracker.view.fragments

import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.calculations.DataGrinder
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.repositories.Repository
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelRecordsWallet
import com.luigivampa92.yms.fintracker.viewmodel.factory.viewModelFactory
import kotlinx.android.synthetic.main.fragment_statistics.*
import java.util.*

class FragmentStatistics : Fragment() {

    private lateinit var mViewModel: ViewModelRecordsWallet
    private val mRecords: MutableList<Record> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
        initComponentsObservers()
    }

    private fun initComponents() {
        mViewModel = ViewModelProviders.of(this,
                viewModelFactory { ViewModelRecordsWallet(Repository(FinanceTrackerDatabase.getInstance(activity!!.application)!!)) })
                .get(ViewModelRecordsWallet::class.java)
    }

    private fun initComponentsListeners() {

        starting_date_fragment_statistics.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(context,
                    R.style.MateriaCalendar,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        starting_date_fragment_statistics.text = this.resources.getString(R.string.formatted_date, dayOfMonth, month, year)
                    },
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH))
            datePickerDialog.show()
        }

        ending_date_fragment_statistics.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(context,
                    R.style.MateriaCalendar,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        ending_date_fragment_statistics.text = this.resources.getString(R.string.formatted_date, dayOfMonth, month, year)
                    },
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH))
            datePickerDialog.show()
        }

        toolbar_fragment_statistics.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    private fun initComponentsObservers(){
        mViewModel.getAllRecords().observe(viewLifecycleOwner, android.arch.lifecycle.Observer {
            it?.forEach { item ->
                mRecords.add(item)
            }
        })
    }

    private fun buildPieChart(it: List<Record>?) {
        val categories = context?.resources?.getStringArray(R.array.categories)
        if(it != null){
            val data = DataGrinder.grindDataForPieChart(it, categories)
        }
    }
}