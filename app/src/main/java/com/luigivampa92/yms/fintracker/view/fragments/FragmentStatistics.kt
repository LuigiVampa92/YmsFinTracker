package com.luigivampa92.yms.fintracker.view.fragments

import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.calculations.DataGrinder
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.repositories.Repository
import com.luigivampa92.yms.fintracker.utils.convertStringToDate
import com.luigivampa92.yms.fintracker.utils.formatDecimalNumber
import com.luigivampa92.yms.fintracker.utils.hasText
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelStatistics
import com.luigivampa92.yms.fintracker.viewmodel.factory.viewModelFactory
import kotlinx.android.synthetic.main.fragment_statistics.*
import java.util.*

class FragmentStatistics : Fragment() {

    private lateinit var mViewModel: ViewModelStatistics
    private var mRecords: MutableList<Record> = mutableListOf()

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
                viewModelFactory { ViewModelStatistics(Repository(FinanceTrackerDatabase.getInstance(activity!!.application)!!)) })
                .get(ViewModelStatistics::class.java)
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

        fab_fragment_statistics.setOnClickListener {
            buildPieChart()
        }
    }

    private fun initComponentsObservers(){
        mRecords.clear()
        mViewModel.getAllRecords().observe(viewLifecycleOwner, android.arch.lifecycle.Observer {
            it?.forEach { item ->
                mRecords.add(item)
            }
        })
    }

    private fun buildPieChart() {

        var records = mRecords
        if(hasText(starting_date_fragment_statistics, ending_date_fragment_statistics)){
            records = DataGrinder.filterByDates(mRecords,
                    convertStringToDate(starting_date_fragment_statistics.text.toString()),
                    convertStringToDate(ending_date_fragment_statistics.text.toString()))
        }

        val hashMap = DataGrinder.splitCategories(records)
        val sum = DataGrinder.getSumForPieChart(records)

        val entries: MutableList<PieEntry> = mutableListOf()
        hashMap.forEach { s, d ->
            entries.add(PieEntry(Math.abs(d.toFloat()), s))
        }

        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.setDrawValues(false)
        pieDataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()


        val legend = chart_fragment_statistics.legend
        legend.textSize = 20.0F
        legend.formSize = 20.0F

        chart_fragment_statistics.data = PieData(pieDataSet)
        chart_fragment_statistics.description = null
        chart_fragment_statistics.animateY(1000, Easing.EasingOption.EaseInOutQuad)
        chart_fragment_statistics.setDrawEntryLabels(false)
        chart_fragment_statistics.holeRadius = 30f
        chart_fragment_statistics.centerText = formatDecimalNumber(sum)
        chart_fragment_statistics.notifyDataSetChanged()
        chart_fragment_statistics.invalidate()
    }
}