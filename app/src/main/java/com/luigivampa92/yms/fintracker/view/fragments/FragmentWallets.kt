package com.luigivampa92.yms.fintracker.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Wallet
import com.luigivampa92.yms.fintracker.model.repositories.WalletsRepository
import com.luigivampa92.yms.fintracker.view.activities.ActivityAddWallet
import com.luigivampa92.yms.fintracker.view.adapters.AdapterWallets
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelWallets
import com.luigivampa92.yms.fintracker.viewmodel.factory.viewModelFactory
import kotlinx.android.synthetic.main.fragment_wallets.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class FragmentWallets : Fragment() {

    private lateinit var mWalletsAdapter: AdapterWallets
    private lateinit var mViewModel: ViewModelWallets

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this,
                viewModelFactory { ViewModelWallets(WalletsRepository(activity!!.application)) }).get(ViewModelWallets::class.java)
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
        mWalletsAdapter = AdapterWallets()
        recycler_fragment_wallets.adapter = mWalletsAdapter
        recycler_fragment_wallets.layoutManager = LinearLayoutManager(context)
    }

    private fun initComponentsListeners() {
        fab_fragment_wallets.setOnClickListener {
            startActivity(Intent(context, ActivityAddWallet::class.java))
        }
    }

    private fun initComponentsObservers() {
        mViewModel.wallets.observe(viewLifecycleOwner, Observer { it ->
            mWalletsAdapter.addAll(it)
        })

    }
}