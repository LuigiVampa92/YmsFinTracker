package com.luigivampa92.yms.fintracker.di.component

import android.content.Context
import com.luigivampa92.yms.fintracker.FinTrackerApplication
import com.luigivampa92.yms.fintracker.di.module.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ContextModule::class,
        AppModule::class,
        RouterModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        DataModule::class
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