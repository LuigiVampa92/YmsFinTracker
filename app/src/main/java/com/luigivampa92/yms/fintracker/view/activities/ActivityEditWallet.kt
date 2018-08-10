package com.luigivampa92.yms.fintracker.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet
import kotlinx.android.synthetic.main.activity_add_record.*
import kotlinx.android.synthetic.main.activity_add_wallet.*

class ActivityEditWallet : ActivityAddWallet() {

    private lateinit var mOldWallet: Wallet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(intent.getParcelableExtra<Wallet>(Constants.WALLET) != null){
            mOldWallet = intent.getParcelableExtra<Wallet>(Constants.WALLET)
            toolbar_activity_add_wallet.title = resources.getString(R.string.edit_wallet)
        }
    }

    override fun makeTransaction(wallet: Wallet) {
        viewModel.editWallet(wallet, mOldWallet)
    }
}
