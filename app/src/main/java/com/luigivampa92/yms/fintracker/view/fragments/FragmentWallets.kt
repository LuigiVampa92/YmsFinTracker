package com.luigivampa92.yms.fintracker.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.view.adapters.AdapterWallets
import kotlinx.android.synthetic.main.fragment_wallets.*

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

    private fun initComponents(){
        mWalletsAdapter = AdapterWallets(childFragmentManager)
        view_pager_fragment_wallets.adapter = mWalletsAdapter

        mWalletsAdapter.addFragment(FragmentWallet())
        mWalletsAdapter.notifyDataSetChanged()
    }

    private fun initComponentsListeners(){
        fab_fragment_wallets.setOnClickListener {
            //Implement setting the selected wallet
            fragmentManager?.popBackStack()
        }
    }
}