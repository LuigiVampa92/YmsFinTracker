package com.luigivampa92.yms.fintracker.view.activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.luigivampa92.yms.fintracker.*
import com.luigivampa92.yms.fintracker.model.Wallet
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelAddWallet
import kotlinx.android.synthetic.main.activity_add_wallet.*

class ActivityAddWallet : AppCompatActivity() {

    private lateinit var mViewModel: ViewModelAddWallet
    private lateinit var mSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_wallet)

        initComponents()
        initComponentsListeners()
    }

    private fun initComponents() {
        mSharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        mViewModel = ViewModelProviders.of(this).get(ViewModelAddWallet::class.java)
        currency_activity_add_wallet.adapter = ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, this.resources.getStringArray(R.array.currencies)
        )
    }

    private fun initComponentsListeners() {

        done_activity_add_wallet.setOnClickListener {
            if (hasText(name_activity_add_wallet) &&
                    isNumeric(balance_activity_add_wallet.text.toString())) {

                mViewModel.addWallet(Wallet(createId(), name_activity_add_wallet.text.toString(),
                        balance_activity_add_wallet.text.toString().toDouble()))

                //Если кошелька по умолчанию нет, то кладем туда только что добавленный
                if (mSharedPreferences.getString(Constants.CURRENT_WALLET, null) == null) {
                    mSharedPreferences.edit().putString(Constants.CURRENT_WALLET, getTextFromView(name_activity_add_wallet)).apply()
                }
                finish()
            }
        }

        toolbar_activity_add_wallet.setOnClickListener {
            super.onBackPressed()
        }
    }
}
