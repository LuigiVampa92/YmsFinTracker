package com.luigivampa92.yms.fintracker.view.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.view.activities.ActivityAddRecord
import com.luigivampa92.yms.fintracker.view.adapters.AdapterRecords
import kotlinx.android.synthetic.main.fragment_balance.*

class FragmentBalance : Fragment() {

    lateinit var mAdapterRecords: AdapterRecords

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_balance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
    }

    private fun initComponents(){
        mAdapterRecords = AdapterRecords()
        recycler_fragment_balance.adapter = mAdapterRecords
    }

    private fun initComponentsListeners() {
        fab_fragment_balance.setOnClickListener {
            startActivity(Intent(context, ActivityAddRecord::class.java))
        }
    }
}