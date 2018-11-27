package com.example.tim.communityclock.di


import com.example.tim.communityclock.App
import com.example.tim.communityclock.data.remote.core.RepositoryModule

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ViewModelModule::class,
    BuildersModule::class,
    DatabaseModule::class,
    RepositoryModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}