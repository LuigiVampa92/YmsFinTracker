package com.luigivampa92.yms.fintracker.ui.account

import android.content.Context
import android.content.Intent
import com.luigivampa92.yms.fintracker.ui.base.NestedFragmentActivity

class AccountActivity : NestedFragmentActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, AccountActivity::class.java)
    }

    override fun createFragment() = AccountFragment.newInstance()
}