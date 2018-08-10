package com.luigivampa92.yms.fintracker.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import com.commit451.modalbottomsheetdialogfragment.ModalBottomSheetDialogFragment
import com.commit451.modalbottomsheetdialogfragment.Option
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.interfaces.IChangeFragmentInterface
import com.luigivampa92.yms.fintracker.interfaces.IFragmentCommunicator
import com.luigivampa92.yms.fintracker.model.repositories.Repository
import com.luigivampa92.yms.fintracker.utils.formatDecimalNumber
import com.luigivampa92.yms.fintracker.view.activities.ActivityAddRecord
import com.luigivampa92.yms.fintracker.view.activities.ActivityAddWallet
import com.luigivampa92.yms.fintracker.view.adapters.AdapterRecords
import com.luigivampa92.yms.fintracker.viewmodel.ViewModelRecordsWallet
import com.luigivampa92.yms.fintracker.viewmodel.factory.viewModelFactory
import kotlinx.android.synthetic.main.abc_dialog_title_material.view.*
import kotlinx.android.synthetic.main.fragment_balance.*
import kotlinx.android.synthetic.main.fragment_add_template.*

class FragmentBalance : Fragment(), ModalBottomSheetDialogFragment.Listener {

    private lateinit var mAdapterRecords: AdapterRecords
    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var mChangeFragmentCallback: IChangeFragmentInterface
    private lateinit var mFragmentCOmmunicator: IFragmentCommunicator
    lateinit var mViewModel: ViewModelRecordsWallet

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mChangeFragmentCallback = context as IChangeFragmentInterface
        mFragmentCOmmunicator = context as IFragmentCommunicator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this,
                viewModelFactory { ViewModelRecordsWallet(Repository(FinanceTrackerDatabase.getInstance(activity!!.application)!!)) }).get(ViewModelRecordsWallet::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_balance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initComponentsListeners()
        initComponentsObservers()
    }

    override fun onResume() {
        super.onResume()
        initComponentsObservers()
    }

    override fun onModalOptionSelected(tag: String?, option: Option) {
        when(option.id){
            R.id.action_templates -> {
                if(!mFragmentCOmmunicator.isTablet()){
                    mChangeFragmentCallback.loadFragment(FragmentTemplates())
                }else{
                    mChangeFragmentCallback.loadFragmentToContainer(FragmentTemplates(), R.id.right_container)
                }
            }
            R.id.action_info -> {
                if(!mFragmentCOmmunicator.isTablet()){
                    mChangeFragmentCallback.loadFragment(FragmentInfo())
                }else{
                    mChangeFragmentCallback.loadFragmentToContainer(FragmentInfo(), R.id.right_container)
                }
            }
            R.id.action_statistics -> {
                if(!mFragmentCOmmunicator.isTablet()){
                    mChangeFragmentCallback.loadFragment(FragmentStatistics())
                }else{
                    mChangeFragmentCallback.loadFragmentToContainer(FragmentStatistics(), R.id.right_container)
                }
            }
            R.id.action_pending_transactions -> {
                if(!mFragmentCOmmunicator.isTablet()){
                    mChangeFragmentCallback.loadFragment(FragmentPendingRecords())
                }else{
                    mChangeFragmentCallback.loadFragmentToContainer(FragmentPendingRecords(), R.id.right_container)
                }
            }
            R.id.action_categories -> {
                if(!mFragmentCOmmunicator.isTablet()){
                    mChangeFragmentCallback.loadFragment(FragmentCategories())
                }else{
                    mChangeFragmentCallback.loadFragmentToContainer(FragmentCategories(), R.id.right_container)
                }
            }
        }
    }

    private fun initComponents() {
        mSharedPreferences = activity!!.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        mAdapterRecords = AdapterRecords(this)
        recycler_fragment_balance.adapter = mAdapterRecords
        bottom_appbar.replaceMenu(R.menu.menu_main)
    }

    private fun initComponentsListeners() {

        bottom_appbar.setNavigationOnClickListener {
            mChangeFragmentCallback.loadFragment(FragmentWallets())
        }

        bottom_appbar.setOnMenuItemClickListener {
            ModalBottomSheetDialogFragment.Builder()
                    .add(R.menu.menu_toolbar_navigation)
                    .show(childFragmentManager, "Options")
            true
        }

        fab_fragment_balance.setOnClickListener {
            if (mSharedPreferences.getString(Constants.CURRENT_WALLET_ID, null) == null) {
                MaterialDialog.Builder(context!!)
                        .title(R.string.create_wallet)
                        .content(R.string.create_wallet_message)
                        .positiveText(R.string.create_wallet_positive_text)
                        .cancelable(false)
                        .negativeText(R.string.create_wallet_negative_text)
                        .onPositive(MaterialDialog.SingleButtonCallback { dialog, _ ->
                            startActivity(Intent(context, ActivityAddWallet::class.java))
                            dialog.dismiss()
                        })
                        .onNegative(MaterialDialog.SingleButtonCallback { dialog, _ ->
                            dialog.dismiss()
                        })
                        .build().show()
            } else {
                startActivity(Intent(context, ActivityAddRecord::class.java))
            }
        }
    }


    private fun initComponentsObservers() {
        val sf = activity!!.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val walletId = sf.getString(Constants.CURRENT_WALLET_ID, null)
        if (walletId != null) {
            mViewModel.getRecordsFromWallet(walletId).observe(viewLifecycleOwner, Observer {
                mAdapterRecords.addAll(it)
            })
            mViewModel.getWallet(walletId).observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    wallet_name_fragment_balance.text = context?.getString(R.string.formatted_wallet_name, it.name)
                    wallet_balance_usd_fragment_balance.text = context?.getString(R.string.formatted_balance_usd,
                            formatDecimalNumber(it.balance))
                    //Всегда из USD конвертируем т.к. дефолтная валюта - доллары
                    wallet_balance_rub_fragment_balance.text = context?.getString(R.string.formatted_balance_rub,
                            formatDecimalNumber(CurrencyConverter.convertCurrency("USD", it.balance, sf.getString(Constants.SECONDARY_CURRENCY, "RUB"))))
                }
            })
        }

    }
}