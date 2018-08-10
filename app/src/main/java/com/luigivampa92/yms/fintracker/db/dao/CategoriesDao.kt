package com.luigivampa92.yms.fintracker.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.luigivampa92.yms.fintracker.model.Category

@Dao
interface CategoriesDao {

    @Insert()
    fun addCategory(category: Category)

    @Query("SELECT * from categories")
    fun getCategories(): LiveData<List<Category>>

    @Delete
    fun deleteCategory(category: Category)
}