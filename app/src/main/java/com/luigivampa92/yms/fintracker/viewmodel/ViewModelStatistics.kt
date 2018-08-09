package com.luigivampa92.yms.fintracker.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.luigivampa92.yms.fintracker.calculations.CurrencyConverter
import com.luigivampa92.yms.fintracker.model.Category
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Template
import com.luigivampa92.yms.fintracker.model.repositories.Repository
import com.luigivampa92.yms.fintracker.model.repositories.TemplatesRepository

class ViewModelStatistics(repository: Repository) : ViewModel() {

    private val mRepository = repository

    fun getAllCurrencies(): LiveData<List<Category>>{
        return mRepository.getCategories()
    }

    fun getAllRecords(): LiveData<List<Record>>{
        return mRepository.getAllRecords()
    }

}