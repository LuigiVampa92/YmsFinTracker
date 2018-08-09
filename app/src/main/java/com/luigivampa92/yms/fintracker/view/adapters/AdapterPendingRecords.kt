package com.luigivampa92.yms.fintracker.view.adapters

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.work.WorkManager
import com.afollestad.materialdialogs.MaterialDialog
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Template
import com.luigivampa92.yms.fintracker.view.activities.ActivityAddRecord
import com.luigivampa92.yms.fintracker.view.fragments.FragmentPendingRecords
import com.luigivampa92.yms.fintracker.view.fragments.FragmentTemplates
import kotlinx.android.synthetic.main.item_record_list.view.*
import java.util.*

class AdapterPendingRecords(fragment: FragmentPendingRecords) : RecyclerView.Adapter<AdapterPendingRecords.ViewHolder>() {

    private val mPendingRecords: MutableList<Record> = mutableListOf()
    private val mFragment = fragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPendingRecords.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_templates_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mPendingRecords[position])


        holder.itemView.setOnLongClickListener {
            MaterialDialog.Builder(it.context)
                    .title(R.string.choose_records_action)
                    .items(R.array.pending_actions)
                    .itemsCallback { dialog, itemView, pos, text ->
                        when (pos) {
                            0 -> {
                                mFragment.viewModel.deleteRecord(mPendingRecords[position])
                                WorkManager.getInstance().cancelAllWork()
                                dialog.dismiss()
                            }
                            else -> {
                                dialog.dismiss()
                            }
                        }
                    }
                    .build().show()
            true
        }
    }

    override fun getItemCount(): Int {
        return mPendingRecords.size
    }

    fun addAll(items: List<Record>?) {
        mPendingRecords.clear()
        items?.let {
            mPendingRecords.addAll(items)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(record: Record) {
            val context = itemView.context
            itemView.item_records_list_name.text = context.resources.getString(R.string.formatted_name, record.name)
            if(record.amount < 0){
                itemView.item_records_list_amount.text = context.resources.getString(R.string.formatted_spent, record.amount.toString(), record.currency)
                itemView.item_records_list_amount.setTextColor(ResourcesCompat.getColor(context.resources, android.R.color.holo_red_dark, null))
            }else{
                itemView.item_records_list_amount.text = context.resources.getString(R.string.formatted_earned, record.amount.toString(), record.currency)
                itemView.item_records_list_amount.setTextColor(ResourcesCompat.getColor(context.resources, android.R.color.holo_green_light, null))
            }

            itemView.item_records_list_category.text = record.category
        }
    }
}