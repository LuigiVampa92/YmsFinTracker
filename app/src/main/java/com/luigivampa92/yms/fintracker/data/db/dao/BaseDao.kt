package com.luigivampa92.yms.fintracker.data.db.dao

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update

interface BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currency: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(currency: T)

    @Delete
    fun delete(currency: T)
}