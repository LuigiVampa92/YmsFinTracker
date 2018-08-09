package com.luigivampa92.yms.fintracker.model.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Category
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Wallet
import kotlinx.coroutines.experimental.launch

class Repository(database: FinanceTrackerDatabase) {

    //На одном экране нужны будут два репозитория, поэтому я решил слить все в один
    private val mDatabase = database

    //Операции с записями
    fun addRecord(record: Record) {
        launch {
            mDatabase.recordsWalletsDao().insertRecordUpdateWalletBalance(record)
        }
    }

    fun editRecord(record: Record, oldRecord: Record) {
        launch {
            mDatabase.recordsWalletsDao().updateRecordUpdateWalletBalance(record, oldRecord)
        }
    }


    fun deleteRecord(record: Record) {
        launch {
            mDatabase.recordsWalletsDao().deleteRecordUpdateWalletBalance(record, record.wallet_id)
        }
    }

    fun getRecordsFromWallet(walletId: String): LiveData<List<Record>> {
        return mDatabase.recordsDao().getAllRecordsFromWallet(walletId)
    }

    fun getAllRecords(): LiveData<List<Record>>{
        return mDatabase.recordsDao().getAllRecords()
    }


    //Операции с кошельками
    fun addWallet(wallet: Wallet) {
        launch {
            mDatabase.walletsDao().addWallet(wallet)
        }
    }

    fun getWallets(): LiveData<List<Wallet>> {
        return mDatabase.walletsDao().getAllWallets()
    }

    fun getWallet(walletId: String): LiveData<Wallet> {
        return mDatabase.walletsDao().getWallet(walletId)
    }

    fun deleteWallet(walletId: String) {
        launch {
            mDatabase.recordsWalletsDao().deleteWalletAndRecords(walletId)
        }
    }

    fun updateWallet(wallet: Wallet){
        launch {
            mDatabase.walletsDao().updateWallet(wallet)
        }
    }

    fun getCategories(): LiveData<List<Category>>{
        return mDatabase.categoriesDao().getCategories()
    }
}