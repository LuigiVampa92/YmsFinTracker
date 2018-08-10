package com.luigivampa92.yms.fintracker.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.interfaces.IChangeFragmentInterface
import com.luigivampa92.yms.fintracker.model.Template
import com.luigivampa92.yms.fintracker.model.repositories.Repository
import com.luigivampa92.yms.fintracker.model.repositories.TemplatesRepository
import com.luigivampa92.yms.fintracker.view.adapters.AdapterPendingRecords
import com.luigivampa92.yms.fintracker.view.adapters.AdapterTemplates
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelRecordsWallet
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelTemplates
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelWallets
import com.luigivampa92.yms.fintracker.viewmodel.factory.viewModelFactory
import kotlinx.android.synthetic.main.fragment_pending_records.*
import kotlinx.android.synthetic.main.fragment_templates.*

class FragmentPendingRecords : Fragment() {

    private lateinit var mAdapter: AdapterPendingRecords
    lateinit var viewModel: ViewModelRecordsWallet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this,
                viewModelFactory { ViewModelRecordsWallet(Repository(FinanceTrackerDatabase.getInstance(activity!!.application)!!)) }).get(ViewModelRecordsWallet::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pending_records, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
        initComponentsObservers()
    }

    private fun initComponents() {
        mAdapter = AdapterPendingRecords(this)
        recycler_fragment_pending_records.adapter = mAdapter
    }

    private fun initComponentsListeners(){

        toolbar_fragment_pending_records.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }

    }

    private fun initComponentsObservers() {
        viewModel.getPendingRecords().observe(viewLifecycleOwner, Observer {
            mAdapter.addAll(it)
        })
    }
}