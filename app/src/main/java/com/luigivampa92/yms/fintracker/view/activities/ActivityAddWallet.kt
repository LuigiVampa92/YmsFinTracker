package com.luigivampa92.yms.fintracker.view.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Wallet
import kotlinx.android.synthetic.main.activity_add_record.*
import kotlinx.android.synthetic.main.activity_add_wallet.*
import kotlinx.coroutines.experimental.launch

class ActivityAddWallet : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_wallet)

        initComponents()
        initComponentsListeners()
    }

    override fun onBackPressed() {
        val sf = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        if(sf.getString(Constants.CURRENT_WALLET, null) != null){
            super.onBackPressed()
        }
    }

    private fun initComponents() {
        currency_activity_add_wallet.adapter = ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, this.resources.getStringArray(R.array.currencies)
        )
    }

    private fun initComponentsListeners() {
        done_activity_add_wallet.setOnClickListener {
            //pass data to db
            val database = FinanceTrackerDatabase.getInstance(this)
            launch {
                database?.walletsDao()?.addWallet(Wallet(null, "Wallet", 100.0))
            }

            finish()
        }

        toolbar_activity_add_wallet.setOnClickListener {
            finish()
        }
    }
}
