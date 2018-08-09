package com.luigivampa92.yms.fintracker.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.repositories.Repository
import com.luigivampa92.yms.fintracker.view.activities.ActivityAddWallet
import com.luigivampa92.yms.fintracker.view.adapters.AdapterWallets
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelWallets
import com.luigivampa92.yms.fintracker.viewmodel.factory.viewModelFactory
import kotlinx.android.synthetic.main.fragment_wallets.*

class FragmentWallets : Fragment() {

    private lateinit var mWalletsAdapter: AdapterWallets
    lateinit var viewModel: ViewModelWallets

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this,
                viewModelFactory { ViewModelWallets(Repository(FinanceTrackerDatabase.getInstance(activity!!.application)!!)) }).get(ViewModelWallets::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wallets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
        initComponentsObservers()
    }

    private fun initComponents() {
        mWalletsAdapter = AdapterWallets(this)
        recycler_fragment_wallets.adapter = mWalletsAdapter
        recycler_fragment_wallets.layoutManager = LinearLayoutManager(context)
    }

    private fun initComponentsListeners() {

        toolbar_fragment_wallets.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }

        fab_fragment_wallets.setOnClickListener {
            startActivity(Intent(context, ActivityAddWallet::class.java))
        }
    }

    private fun initComponentsObservers() {
        viewModel.wallets.observe(viewLifecycleOwner, Observer { it ->
            mWalletsAdapter.addAll(it)
        })

    }
}