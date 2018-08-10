package com.luigivampa92.yms.fintracker.model.repositories

import android.arch.lifecycle.LiveData
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Template
import kotlinx.coroutines.experimental.launch

class TemplatesRepository(database: FinanceTrackerDatabase) {

    private val mDatabase = database


    fun deleteTemplate(template: Template) {
        launch {
            mDatabase.templatesDao().deleteTemplate(template)
        }
    }

    fun addTemplate(template: Template) {
        launch {
            mDatabase.templatesDao().addTemplate(template)
        }
    }

    fun getAllTemplates(): LiveData<List<Template>> {
        return mDatabase.templatesDao().getAllTemplates()
    }


}