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
import com.luigivampa92.yms.fintracker.view.adapters.AdapterTemplates
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelTemplates
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelWallets
import com.luigivampa92.yms.fintracker.viewmodel.factory.viewModelFactory
import kotlinx.android.synthetic.main.fragment_templates.*

class FragmentTemplates : Fragment() {

    private lateinit var mAdapter: AdapterTemplates
    private lateinit var mFragmentLoader: IChangeFragmentInterface
    lateinit var viewModel: ViewModelTemplates

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mFragmentLoader = context as IChangeFragmentInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this,
                viewModelFactory { ViewModelTemplates(TemplatesRepository(FinanceTrackerDatabase.getInstance(activity!!.application)!!)) }).get(ViewModelTemplates::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_templates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
        initComponentsObservers()
    }

    private fun initComponents() {
        mAdapter = AdapterTemplates(this)
        recycler_fragment_templates.adapter = mAdapter
    }

    private fun initComponentsListeners(){

        toolbar_fragment_templates.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }

        fab_fragment_templates.setOnClickListener {
            mFragmentLoader.loadFragment(FragmentAddTemplate())
        }
    }

    private fun initComponentsObservers() {
        viewModel.templates.observe(viewLifecycleOwner, Observer {
            mAdapter.addAll(it)
        })
    }
}