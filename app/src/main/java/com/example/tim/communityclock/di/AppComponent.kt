package com.example.tim.communityclock.di


import com.example.tim.communityclock.App
import com.example.tim.communityclock.ui.alarmdisplay.AlarmDisplayActivityModule
import com.example.tim.communityclock.ui.main.MainModule

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ViewModelModule::class,
    MainModule::class,
    AlarmDisplayActivityModule::class,
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