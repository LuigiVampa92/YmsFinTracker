package com.luigivampa92.yms.fintracker.view.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet
import kotlinx.android.synthetic.main.item_record_list.view.*
import kotlinx.android.synthetic.main.item_wallets_list.view.*


class AdapterWallets : RecyclerView.Adapter<AdapterWallets.ViewHolder>() {

    private val mWalletsList: MutableList<Wallet> = mutableListOf()
    private var mColoredItemPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterWallets.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wallets_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sf = holder.itemView.context.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        if (sf.getString(Constants.CURRENT_WALLET, null) == mWalletsList[position].id) {
            holder.itemView.layout_item_wallets_list.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, android.R.color.holo_orange_light))
            mColoredItemPosition = position
        } else {
            holder.itemView.layout_item_wallets_list.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, android.R.color.white))
        }
        holder.bind(mWalletsList[position])

        holder.itemView.setOnClickListener {
            it.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, android.R.color.holo_orange_light))
            sf.edit().putString(Constants.CURRENT_WALLET, mWalletsList[position].id).apply()
            notifyItemChanged(position)
            notifyItemChanged(mColoredItemPosition)
        }
    }

    override fun getItemCount(): Int {
        return mWalletsList.size
    }

    fun addAll(items: List<Wallet>?) {
        items?.forEach {
            if (!contains(it)) {
                mWalletsList.add(it)
                notifyItemInserted(mWalletsList.size - 1)
            }
        }
    }

    fun contains(wallet: Wallet): Boolean {
        mWalletsList.forEach {
            if (it.id == wallet.id) return true
        }
        return false
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(wallet: Wallet) {
            itemView.name_item_wallets_list.text = wallet.name
            itemView.balance_item_wallets_list.text = wallet.balance.toString()
        }

    }
}