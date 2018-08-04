package com.luigivampa92.yms.fintracker.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Wallet
import com.luigivampa92.yms.fintracker.view.activities.ActivityAddWallet
import com.luigivampa92.yms.fintracker.view.adapters.AdapterWallets
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelWallets
import kotlinx.android.synthetic.main.fragment_wallets.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class FragmentWallets : Fragment() {

    private lateinit var mWalletsAdapter: AdapterWallets
    private lateinit var mViewModel: ViewModelWallets

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(ViewModelWallets::class.java)
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
        mWalletsAdapter = AdapterWallets(childFragmentManager)
        view_pager_fragment_wallets.adapter = mWalletsAdapter
        view_pager_fragment_wallets.clipToPadding = false
        view_pager_fragment_wallets.setPadding(50, 0, 50, 0)
        view_pager_fragment_wallets.pageMargin = 30
    }

    private fun initComponentsListeners() {
        fab_fragment_wallets.setOnClickListener {
            startActivity(Intent(context, ActivityAddWallet::class.java))
        }
    }

    private fun initComponentsObservers() {
        mViewModel.getWallets().observe(viewLifecycleOwner, Observer { it ->
            it?.forEach {
                mWalletsAdapter.addFragment(it)
                mWalletsAdapter.notifyDataSetChanged()
            }
        })

    }
}