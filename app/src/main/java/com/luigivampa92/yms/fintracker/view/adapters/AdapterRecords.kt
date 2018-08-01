package com.luigivampa92.yms.fintracker.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.model.Record


class AdapterRecords : RecyclerView.Adapter<AdapterRecords.ViewHolder>() {

    val mRecordsList: MutableList<Record> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRecords.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_record_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }


    fun addAll(items: List<Record>) {
        items.forEach {
            mRecordsList.add(it)
            notifyItemInserted(mRecordsList.size - 1)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(record: Record) {
        }
    }
}