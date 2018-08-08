package com.luigivampa92.yms.fintracker.view.adapters

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.model.Template
import com.luigivampa92.yms.fintracker.view.activities.ActivityAddRecord
import com.luigivampa92.yms.fintracker.view.fragments.FragmentTemplates
import kotlinx.android.synthetic.main.item_record_list.view.*

class AdapterTemplates(fragment: FragmentTemplates) : RecyclerView.Adapter<AdapterTemplates.ViewHolder>() {

    private val mTemplatesList: MutableList<Template> = mutableListOf()
    private val mFragment = fragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTemplates.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_templates_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mTemplatesList[position])
        holder.itemView.setOnLongClickListener(View.OnLongClickListener {
            MaterialDialog.Builder(it.context)
                    .title(R.string.choose_records_action)
                    .items(R.array.records_actions)
                    .itemsCallback { dialog, itemView, pos, text ->
                        when (pos) {
                            0 -> {
                                val intent = Intent(it.context, ActivityAddRecord::class.java)
                                intent.putExtra(Constants.TEMPLATE, mTemplatesList[position])
                                it.context.startActivity(intent)
                                dialog.dismiss()
                            }
                            else -> {

                                dialog.dismiss()
                            }
                        }
                    }
                    .build().show()
            true
        })
    }

    override fun getItemCount(): Int {
        return mTemplatesList.size
    }

    fun addAll(items: List<Template>?) {
        mTemplatesList.clear()
        items?.let {
            mTemplatesList.addAll(items)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(template: Template) {
            itemView.item_records_list_name.text = template.name
            itemView.item_records_list_amount.text = template.amount.toString()
        }
    }
}