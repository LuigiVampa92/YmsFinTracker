package com.luigivampa92.yms.fintracker.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import com.afollestad.materialdialogs.MaterialDialog
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.formatDecimalNumber
import com.luigivampa92.yms.fintracker.view.activities.ActivityAddRecord
import com.luigivampa92.yms.fintracker.view.activities.ActivityAddWallet
import com.luigivampa92.yms.fintracker.view.adapters.AdapterRecords
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelRecords
import kotlinx.android.synthetic.main.fragment_balance.*

class FragmentBalance : Fragment(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var mAdapterRecords: AdapterRecords
    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var mViewModel: ViewModelRecords


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_balance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
        initComponentsObservers()
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        initComponentsObservers()
    }

    private fun initComponents() {
        mSharedPreferences = activity!!.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        mViewModel = ViewModelProviders.of(activity!!).get(ViewModelRecords::class.java)
        mAdapterRecords = AdapterRecords()
        recycler_fragment_balance.adapter = mAdapterRecords
    }

    private fun initComponentsListeners() {

        fab_fragment_balance.setOnClickListener {
            if (mSharedPreferences.getString(Constants.CURRENT_WALLET, null) == null) {
                MaterialDialog.Builder(context!!)
                        .title(R.string.create_wallet)
                        .content(R.string.create_wallet_message)
                        .positiveText(R.string.create_wallet_positive_text)
                        .cancelable(false)
                        .negativeText(R.string.create_wallet_negative_text)
                        .onPositive(MaterialDialog.SingleButtonCallback { dialog, _ ->
                            startActivity(Intent(context, ActivityAddWallet::class.java))
                            dialog.dismiss()
                        })
                        .onNegative(MaterialDialog.SingleButtonCallback { dialog, _ ->
                            dialog.dismiss()
                        })
                        .build().show()
            } else {
                startActivity(Intent(context, ActivityAddRecord::class.java))
            }
        }
    }


    private fun initComponentsObservers() {
        val sf = activity!!.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val walletId = sf.getString(Constants.CURRENT_WALLET, null)
        if (walletId != null) {
            mViewModel.getRecordsFromWallet(walletId).observe(viewLifecycleOwner, Observer {
                mAdapterRecords.addAll(it)
            })
            mViewModel.getWallet(walletId).observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    wallet_name_fragment_balance.text = context?.getString(R.string.formatted_wallet_name, it.name)
                    wallet_balance_usd_fragment_balance.text = context?.getString(R.string.formatted_balance_usd, formatDecimalNumber(it.balance))
                    wallet_balance_rub_fragment_balance.text = context?.getString(R.string.formatted_balance_rub, formatDecimalNumber(CurrencyConverter.convertToRoubles(it.balance)))
                }
            })
        }

    }
}