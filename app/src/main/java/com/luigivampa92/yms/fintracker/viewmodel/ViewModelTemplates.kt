package com.luigivampa92.yms.fintracker.viewmodel

import android.arch.lifecycle.ViewModel
import com.luigivampa92.yms.fintracker.model.Template
import com.luigivampa92.yms.fintracker.model.repositories.TemplatesRepository

class ViewModelTemplates(repository: TemplatesRepository) : ViewModel() {

    private val mRepository = repository

    val templates = mRepository.getAllTemplates()

    fun deleteTemplate(template: Template){
        mRepository.deleteTemplate(template)
    }

    fun addTemplate(template: Template){
        mRepository.addTemplate(template)
    }

}