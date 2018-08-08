package com.luigivampa92.yms.fintracker.view.adapters

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private lateinit var mSharedPreferences: SharedPreferences

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTemplates.ViewHolder {
        mSharedPreferences = parent.context.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_templates_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mTemplatesList[position])

        holder.itemView.setOnClickListener {
            if (mSharedPreferences.getString(Constants.CURRENT_WALLET_ID, null) == null) {
                Toast.makeText(it.context, R.string.add_wallet_to_add_template, Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(it.context, ActivityAddRecord::class.java)
                intent.putExtra(Constants.TEMPLATE, mTemplatesList[position])
                it.context.startActivity(intent)
            }
        }

        holder.itemView.setOnLongClickListener(View.OnLongClickListener {
            MaterialDialog.Builder(it.context)
                    .title(R.string.choose_records_action)
                    .items(R.array.templates_actions)
                    .itemsCallback { dialog, itemView, pos, text ->
                        when (pos) {
                            0 -> {
                                mFragment.viewModel.deleteTemplate(mTemplatesList[position])
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