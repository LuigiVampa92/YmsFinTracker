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
import com.luigivampa92.yms.fintracker.model.Category
import com.luigivampa92.yms.fintracker.model.Template
import com.luigivampa92.yms.fintracker.view.activities.ActivityAddRecord
import com.luigivampa92.yms.fintracker.view.fragments.FragmentCategories
import com.luigivampa92.yms.fintracker.view.fragments.FragmentTemplates
import kotlinx.android.synthetic.main.item_category_list.view.*
import kotlinx.android.synthetic.main.item_record_list.view.*

class AdapterCategories(fragment: FragmentCategories) : RecyclerView.Adapter<AdapterCategories.ViewHolder>() {

    private val mCategoriesList: MutableList<Category> = mutableListOf()
    private val mFragment = fragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCategories.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mCategoriesList[position])

        holder.itemView.setOnLongClickListener(View.OnLongClickListener {
            MaterialDialog.Builder(it.context)
                    .title(R.string.choose_records_action)
                    .items(R.array.templates_actions)
                    .itemsCallback { dialog, itemView, pos, text ->
                        when (pos) {
                            0 -> {
                                mFragment.viewModel.deleteCategory(mCategoriesList[position])
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
        return mCategoriesList.size
    }

    fun addAll(items: List<Category>?) {
        mCategoriesList.clear()
        items?.let {
            mCategoriesList.addAll(items)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category) {
            itemView.name_item_category_list.text = category.name
        }
    }
}