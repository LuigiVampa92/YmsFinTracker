package com.luigivampa92.yms.fintracker.ui.addrecord

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.ui.base.BaseFragment
import android.app.DatePickerDialog
import java.text.SimpleDateFormat
import java.util.*

class AddRecordFragment : BaseFragment() {

    companion object {
        fun newInstance(): AddRecordFragment {
            val fragment = AddRecordFragment()
            fragment.setHasOptionsMenu(true)
            return fragment
        }
    }

    private lateinit var unbinder: Unbinder
    @BindView(R.id.include_toolbar)
    protected lateinit var toolbar: Toolbar
    @BindView(R.id.edit_text_amount)
    protected lateinit var editTextAmount: EditText
    @BindView(R.id.edit_text_date)
    protected lateinit var editTextDate: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_add_record, container, false).also {
                unbinder = ButterKnife.bind(this, it)
                setupToolbar()

                editTextAmount.setText("0")

                val formatted = SimpleDateFormat.getDateInstance().format(Date())
                editTextDate.setText(formatted)
            }

    override fun onDestroyView() {
        unbinder.unbind()
        super.onDestroyView()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            activity?.finish()
            return true
        }
        else return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        val addRecordActivity = activity as AddRecordActivity
        addRecordActivity.setSupportActionBar(toolbar)
        addRecordActivity.supportActionBar?.setDisplayShowHomeEnabled(true)
        addRecordActivity.supportActionBar?.setHomeButtonEnabled(true)
        addRecordActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @OnClick(R.id.edit_text_date)
    protected fun editTextDateClicked() {
        val calendar = Calendar.getInstance()

        val dateListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val date = calendar.time
            val formatted = SimpleDateFormat.getDateInstance().format(date)
            editTextDate.setText(formatted)
        }

        DatePickerDialog(activity, R.style.DatePickerTheme, dateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }
}