package com.luigivampa92.yms.fintracker.view.activities

import android.os.Bundle
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.model.Record
import kotlinx.android.synthetic.main.activity_add_record.*

class ActivityEditRecord : ActivityAddRecord() {

    private lateinit var mOldRecord: Record

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.getParcelableExtra<Record>(Constants.RECORD) != null) {
            mOldRecord = intent.getParcelableExtra<Record>(Constants.RECORD)
            toolbar_activity_add_record.title = resources.getString(R.string.edit_record)
        }
    }

    override fun makeTransaction(record: Record) {
        record.id = mOldRecord.id
        viewModel.editRecord(record, mOldRecord)
    }
}
