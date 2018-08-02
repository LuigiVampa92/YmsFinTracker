package com.luigivampa92.yms.fintracker.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.luigivampa92.yms.fintracker.R
import kotlinx.android.synthetic.main.activity_add_record.*
import kotlinx.android.synthetic.main.activity_add_wallet.*

class ActivityAddWallet : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_wallet)

        initComponents()
        initComponentsListeners()
    }

    private fun initComponents() {
        currency_activity_add_wallet.adapter = ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, this.resources.getStringArray(R.array.currencies)
        )
    }

    private fun initComponentsListeners() {
        done_activity_add_wallet.setOnClickListener {
            //pass data to db
            finish()
        }

        toolbar_activity_add_wallet.setOnClickListener {
            finish()
        }
    }
}
