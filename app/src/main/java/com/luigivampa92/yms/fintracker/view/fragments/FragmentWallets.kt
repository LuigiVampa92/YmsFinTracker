package com.luigivampa92.yms.fintracker.view.fragments

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
import kotlinx.android.synthetic.main.fragment_wallets.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class FragmentWallets : Fragment() {

    private lateinit var mWalletsAdapter: AdapterWallets

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wallets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
    }

    override fun onResume() {
        super.onResume()
        val database = FinanceTrackerDatabase.getInstance(activity!!)
        launch {
            val wallets = database?.walletsDao()?.getAllWallets()
            launch(UI) {
                wallets?.forEach {
                    val fragment = FragmentWallet()
                    val bundle = Bundle()
                    bundle.putString(Constants.NAME, it.name)
                    bundle.putString(Constants.BALANCE, it.balance.toString())
                    fragment.arguments = bundle
                    mWalletsAdapter.addFragment(fragment)
                    mWalletsAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun initComponents() {
        mWalletsAdapter = AdapterWallets(childFragmentManager)
        view_pager_fragment_wallets.adapter = mWalletsAdapter

        mWalletsAdapter.addFragment(FragmentWallet())
        mWalletsAdapter.notifyDataSetChanged()
    }

    private fun initComponentsListeners() {

        fab_fragment_wallets.setOnClickListener {
            startActivity(Intent(context, ActivityAddWallet::class.java))
        }

    }
}