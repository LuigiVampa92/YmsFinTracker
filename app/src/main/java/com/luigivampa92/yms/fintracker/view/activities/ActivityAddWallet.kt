package com.luigivampa92.yms.fintracker.view.activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.luigivampa92.yms.fintracker.*
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Wallet
import com.luigivampa92.yms.fintracker.model.repositories.Repository
import com.luigivampa92.yms.fintracker.utils.createId
import com.luigivampa92.yms.fintracker.utils.getTextFromView
import com.luigivampa92.yms.fintracker.utils.hasText
import com.luigivampa92.yms.fintracker.utils.isNumeric
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelAddRecord
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelAddWallet
import com.luigivampa92.yms.fintracker.viewmodel.factory.viewModelFactory
import kotlinx.android.synthetic.main.activity_add_wallet.*

class ActivityAddWallet : AppCompatActivity() {

    private lateinit var mViewModel: ViewModelAddWallet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_wallet)

        initComponents()
        initComponentsListeners()
    }

    private fun initComponents() {
        val database = FinanceTrackerDatabase.getInstance(this.application)
        mViewModel = ViewModelProviders.of(this,
                viewModelFactory { ViewModelAddWallet(Repository(database!!)) }).get(ViewModelAddWallet::class.java)

        currency_activity_add_wallet.adapter = ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, this.resources.getStringArray(R.array.currencies)
        )
    }

    private fun initComponentsListeners() {

        done_activity_add_wallet.setOnClickListener { _ ->
            if (hasText(name_activity_add_wallet) &&
                    isNumeric(balance_activity_add_wallet.text.toString())) {

                //Всегда конвертируем в доллары
                val walletId = createId()
                val walletName = getTextFromView(name_activity_add_wallet)
                val walletCurrency = currency_activity_add_wallet.selectedItem.toString()
                val walletBalance = CurrencyConverter.convertCurrency(
                        walletCurrency,
                        getTextFromView(balance_activity_add_wallet).toDouble(),
                        "USD"
                )

                mViewModel.addWallet(Wallet(
                        walletId, walletName, walletBalance, walletCurrency
                ))


                //Если кошельков не было, то добавляем этот как дефолтный
                val sf = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
                if (sf.getString(Constants.CURRENT_WALLET_ID, null) == null) {
                    sf.edit().putString(Constants.CURRENT_WALLET_ID, walletId).apply()
                }

                finish()
            }
        }

        toolbar_activity_add_wallet.setOnClickListener {
            super.onBackPressed()
        }
    }
}
