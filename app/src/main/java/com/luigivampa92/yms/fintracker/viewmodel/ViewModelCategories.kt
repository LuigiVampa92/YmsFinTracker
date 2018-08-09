package com.luigivampa92.yms.fintracker.viewmodel

import android.arch.lifecycle.ViewModel
import com.luigivampa92.yms.fintracker.model.Category
import com.luigivampa92.yms.fintracker.model.repositories.CategoriesRepository
import com.luigivampa92.yms.fintracker.model.repositories.TemplatesRepository

class ViewModelCategories(repository: CategoriesRepository) : ViewModel() {

    private val mRepository = repository

    val categories = mRepository.getAllCategories()

    fun deleteCategory(category: Category){
        mRepository.deleteCategory(category)
    }

    fun addCategory(category: Category){
        mRepository.addCategory(category)
    }

}