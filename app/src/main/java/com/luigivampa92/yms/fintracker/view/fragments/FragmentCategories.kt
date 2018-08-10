package com.luigivampa92.yms.fintracker.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.afollestad.materialdialogs.MaterialDialog
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Category
import com.luigivampa92.yms.fintracker.model.repositories.CategoriesRepository
import com.luigivampa92.yms.fintracker.view.adapters.AdapterCategories
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelCategories
import com.luigivampa92.yms.fintracker.viewmodel.factory.viewModelFactory
import kotlinx.android.synthetic.main.fragment_categories.*

class FragmentCategories: Fragment(){

    private lateinit var mAdapter: AdapterCategories
    lateinit var viewModel: ViewModelCategories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this,
                viewModelFactory { ViewModelCategories(CategoriesRepository(FinanceTrackerDatabase.getInstance(activity!!.application)!!)) }).get(ViewModelCategories::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
        initComponentsObservers()
    }

    private fun initComponents() {
        mAdapter = AdapterCategories(this)
        recycler_fragment_categories.adapter = mAdapter
    }

    private fun initComponentsListeners(){

        toolbar_fragment_categories.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }

        fab_fragment_categories.setOnClickListener {
            var income = false
            MaterialDialog.Builder(it.context)
                    .title(R.string.create_category)
                    .inputRangeRes(2, 20, R.color.colorAccent)
                    .checkBoxPromptRes(R.string.income, false, CompoundButton.OnCheckedChangeListener { _, b ->
                        income = b
                    })
                    .input(R.string.category_name, R.string.category_prefill, MaterialDialog.InputCallback { dialog, input ->
                        viewModel.addCategory(Category(0, input.toString(), income))
                    })
                    .build()
                    .show()
        }
    }

    private fun initComponentsObservers() {
        viewModel.categories.observe(viewLifecycleOwner, Observer {
            mAdapter.addAll(it)
        })
    }
}