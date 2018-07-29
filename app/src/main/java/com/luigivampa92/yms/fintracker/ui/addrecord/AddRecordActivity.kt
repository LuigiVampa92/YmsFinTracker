package com.luigivampa92.yms.fintracker.ui.addrecord

import android.content.Context
import android.content.Intent
import com.luigivampa92.yms.fintracker.ui.base.NestedFragmentActivity

class AddRecordActivity : NestedFragmentActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, AddRecordActivity::class.java)
    }

    override fun createFragment() = AddRecordFragment.newInstance()
}