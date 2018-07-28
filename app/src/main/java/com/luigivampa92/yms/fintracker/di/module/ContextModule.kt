package com.luigivampa92.yms.fintracker.di.module

import com.luigivampa92.yms.fintracker.di.module.screen.MainActivityModule
import com.luigivampa92.yms.fintracker.di.scope.ActivityScope
import com.luigivampa92.yms.fintracker.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [(AndroidSupportInjectionModule::class)])
abstract class ContextModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun mainActivityInjector(): MainActivity
}