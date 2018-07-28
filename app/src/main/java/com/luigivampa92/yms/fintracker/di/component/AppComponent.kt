package com.luigivampa92.yms.fintracker.di.component

import android.content.Context
import com.luigivampa92.yms.fintracker.FinTrackerApplication
import com.luigivampa92.yms.fintracker.di.module.AppModule
import com.luigivampa92.yms.fintracker.di.module.ContextModule
import com.luigivampa92.yms.fintracker.di.module.RouterModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ContextModule::class,
        AppModule::class,
        RouterModule::class
))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(app: FinTrackerApplication)
}