package com.luigivampa92.yms.fintracker.view.adapters

import android.content.Intent
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.view.activities.ActivityEditRecord
import com.luigivampa92.yms.fintracker.view.fragments.FragmentBalance
import kotlinx.android.synthetic.main.item_record_list.view.*


class AdapterRecords(fragmentBalance: FragmentBalance) : RecyclerView.Adapter<AdapterRecords.ViewHolder>() {

    private val mRecordsList: MutableList<Record> = mutableListOf()
    private val mFragment = fragmentBalance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRecords.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_record_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mRecordsList[position])
        holder.itemView.setOnLongClickListener(View.OnLongClickListener {
            MaterialDialog.Builder(it.context)
                    .title(R.string.choose_records_action)
                    .items(R.array.records_actions)
                    .itemsCallback { dialog, itemView, pos, text ->
                        when (pos) {
                            0 -> {
                                val intent = Intent(it.context, ActivityEditRecord::class.java)
                                intent.putExtra(Constants.RECORD, mRecordsList[position])
                                it.context.startActivity(intent)
                                dialog.dismiss()
                            }
                            else -> {
                                mFragment.mViewModel.deleteRecord(mRecordsList[position])
                                dialog.dismiss()
                            }
                        }
                    }
                    .build().show()
            true
        })
    }

    override fun getItemCount(): Int {
        return mRecordsList.size
    }

    fun addAll(items: List<Record>?) {
        mRecordsList.clear()
        items?.let {
            mRecordsList.addAll(items)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(record: Record) {
            val context = itemView.context
            itemView.item_records_list_name.text = context.resources.getString(R.string.formatted_name, record.name)
            itemView.item_records_list_date.text = context.getString(R.string.formatted_date_label, record.date)
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