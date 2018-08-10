package com.luigivampa92.yms.fintracker.model.repositories

import android.arch.lifecycle.LiveData
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Category
import com.luigivampa92.yms.fintracker.model.Template
import kotlinx.coroutines.experimental.launch

class CategoriesRepository(database: FinanceTrackerDatabase) {

    private val mDatabase = database


    fun deleteCategory(category: Category) {
        launch {
            mDatabase.categoriesDao().deleteCategory(category)
        }
    }

    fun addCategory(category: Category) {
        launch {
            mDatabase.categoriesDao().addCategory(category)
        }
    }

    fun getAllCategories(): LiveData<List<Category>> {
        return mDatabase.categoriesDao().getCategories()
    }


}