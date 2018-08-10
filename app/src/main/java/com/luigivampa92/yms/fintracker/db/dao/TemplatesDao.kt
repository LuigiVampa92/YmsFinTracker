package com.luigivampa92.yms.fintracker.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.luigivampa92.yms.fintracker.model.Template

@Dao
interface TemplatesDao{

    @Insert
    fun addTemplate(template: Template)

    @Delete
    fun deleteTemplate(template: Template)

    @Query("SELECT * from templates")
    fun getAllTemplates() : LiveData<List<Template>>
}
