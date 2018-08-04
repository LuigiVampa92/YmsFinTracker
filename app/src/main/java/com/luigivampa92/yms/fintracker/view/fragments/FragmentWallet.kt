package com.luigivampa92.yms.fintracker.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import kotlinx.android.synthetic.main.fragment_wallet.*

class FragmentWallet : Fragment() {

    private lateinit var mFragmentId: String
    private lateinit var mCurrentWalletID: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents(arguments)
        initComponentsListeners()
    }

    private fun initComponents(bundle: Bundle?) {
        mCurrentWalletID = activity!!.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE).getString(Constants.CURRENT_WALLET, null)
        if (bundle != null) {
            mFragmentId = bundle.getString(Constants.ID)
            name_fragment_wallet.text = bundle.getString(Constants.NAME)
            balance_fragment_wallet.text = bundle.getString(Constants.BALANCE)
        }
        if(mCurrentWalletID == mFragmentId){
            current_fragment_wallet.text = resources.getString(R.string.primary)
        }
    }

    private fun initComponentsListeners(){
        make_current_fragment_wallet.setOnClickListener {
            val sf = activity!!.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
            sf.edit().putString(Constants.CURRENT_WALLET, mFragmentId).apply()
            current_fragment_wallet.text = resources.getString(R.string.primary)
        }
    }
}