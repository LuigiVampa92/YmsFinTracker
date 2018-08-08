package com.luigivampa92.yms.fintracker.view.adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.model.Wallet
import com.luigivampa92.yms.fintracker.utils.formatDecimalNumber
import com.luigivampa92.yms.fintracker.view.activities.ActivityEditRecord
import com.luigivampa92.yms.fintracker.view.activities.ActivityEditWallet
import com.luigivampa92.yms.fintracker.view.fragments.FragmentWallets
import kotlinx.android.synthetic.main.item_wallets_list.view.*


class AdapterWallets(fragment: FragmentWallets) : RecyclerView.Adapter<AdapterWallets.ViewHolder>() {

    private val mFragment = fragment
    private val mWalletsList: MutableList<Wallet> = mutableListOf()
    private var mColoredItemPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterWallets.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wallets_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val sf = holder.itemView.context.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        if (sf.getString(Constants.CURRENT_WALLET_ID, null) == mWalletsList[position].id) {
            holder.itemView.layout_item_wallets_list.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, android.R.color.holo_orange_light))
            mColoredItemPosition = holder.adapterPosition
        } else {
            holder.itemView.layout_item_wallets_list.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, android.R.color.white))
        }
        holder.bind(mWalletsList[holder.adapterPosition])

        holder.itemView.setOnClickListener {
            it.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, android.R.color.holo_orange_light))
            sf.edit().putString(Constants.CURRENT_WALLET_ID, mWalletsList[holder.adapterPosition].id).apply()
            notifyItemChanged(holder.adapterPosition)
            notifyItemChanged(mColoredItemPosition)
        }

        holder.itemView.setOnLongClickListener {
            MaterialDialog.Builder(it.context)
                    .title(R.string.choose_records_action)
                    .items(R.array.records_actions)
                    .itemsCallback { dialog, itemView, pos, text ->
                        when (pos) {
                            0 -> {
                                val intent = Intent(it.context, ActivityEditWallet::class.java)
                                intent.putExtra(Constants.WALLET, mWalletsList[position])
                                it.context.startActivity(intent)
                                dialog.dismiss()
                            }
                            else -> {
                                if(mWalletsList.size == 1){
                                    sf.edit().putString(Constants.CURRENT_WALLET_ID, null).apply()
                                }else{
                                    sf.edit().putString(Constants.CURRENT_WALLET_ID, mWalletsList.first().id).apply()
                                }
                                mFragment.viewModel.deleteWallet(mWalletsList[position])
                                dialog.dismiss()
                            }
                        }
                    }
                    .build().show()
            true
        }
    }

    override fun getItemCount(): Int {
        return mWalletsList.size
    }

    fun addAll(items: List<Wallet>?) {
        mWalletsList.clear()
        items?.let {
            mWalletsList.addAll(items)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(wallet: Wallet) {
            itemView.name_item_wallets_list.text = wallet.name
            itemView.balance_item_wallets_list.text = formatDecimalNumber(wallet.balance)
            itemView.balance_secondary_item_wallets_list.text = formatDecimalNumber(CurrencyConverter.convertCurrency(
                    "USD", wallet.balance, itemView.context.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE).getString(Constants.SECONDARY_CURRENCY, "RUB")))
        }

    }
}