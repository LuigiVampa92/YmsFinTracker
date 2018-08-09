package com.luigivampa92.yms.fintracker.view.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.luigivampa92.yms.fintracker.R
import kotlinx.android.synthetic.main.activity_add_record.*
import kotlinx.android.synthetic.main.fragment_statistics.*
import java.util.*

class FragmentStatistics : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
    }

    private fun initComponents() {

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
}