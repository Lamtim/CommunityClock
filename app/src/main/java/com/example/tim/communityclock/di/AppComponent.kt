package com.example.tim.communityclock.di


import com.example.tim.communityclock.App

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    DatabaseModule::class,
    BuildersModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}