package com.luigivampa92.yms.fintracker.view.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import com.afollestad.materialdialogs.MaterialDialog
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.view.activities.ActivityAddRecord
import com.luigivampa92.yms.fintracker.view.activities.ActivityAddWallet
import com.luigivampa92.yms.fintracker.view.adapters.AdapterRecords
import kotlinx.android.synthetic.main.fragment_balance.*

class FragmentBalance : Fragment() {

    private lateinit var mAdapterRecords: AdapterRecords
    private var mSharedPreferences: SharedPreferences? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_balance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
    }

    override fun onResume() {
        super.onResume()

        if (mSharedPreferences?.getString(Constants.SHARED_PREFERENCES, null) == null) {
            //show dialog launching to add wallet activity
            MaterialDialog.Builder(context!!)
                    .title(R.string.create_wallet)
                    .content(R.string.create_wallet_message)
                    .positiveText(R.string.create_wallet_positive_text)
                    .negativeText(R.string.create_wallet_negative_text)
                    .onPositive(MaterialDialog.SingleButtonCallback { dialog, which ->
                        startActivity(Intent(context, ActivityAddWallet::class.java))
                        dialog.dismiss()
                    })
                    .onNegative(MaterialDialog.SingleButtonCallback { dialog, which ->
                        dialog.dismiss()
                    })
                    .show()
        }
    }

    private fun initComponents() {
        mSharedPreferences = context?.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        mAdapterRecords = AdapterRecords()
        recycler_fragment_balance.adapter = mAdapterRecords
    }

    private fun initComponentsListeners() {

        fab_fragment_balance.setOnClickListener {
            startActivity(Intent(context, ActivityAddRecord::class.java))
        }
    }
}