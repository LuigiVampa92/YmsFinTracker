package com.luigivampa92.yms.fintracker.view.activities


import android.os.Bundle
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.model.Record

class ActivityEditRecord : ActivityAddRecord() {

    private lateinit var mOldRecord: Record

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mOldRecord = intent.getParcelableExtra<Record>(Constants.RECORD)
    }
}
