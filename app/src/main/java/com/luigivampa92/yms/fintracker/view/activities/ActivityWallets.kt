package com.luigivampa92.yms.fintracker.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.luigivampa92.yms.fintracker.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_wallets.*

class ActivityWallets : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallets)

        initComponents()
        initComponentsListeners()
    }

    private fun initComponents(){
        toolbar_activity_wallets.setNavigationIcon(R.drawable.ic_dehaze)
        toolbar_activity_wallets.title = resources.getString(R.string.your_wallets)
        toolbar_activity_wallets.setNavigationIcon(R.drawable.ic_arrow_back)
    }

    private fun initComponentsListeners(){}
}
