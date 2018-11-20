package com.example.tim.communityclock.di

import com.example.tim.communityclock.ui.alarmdisplay.AlarmDisplayActivity
import com.example.tim.communityclock.ui.alarmdisplay.AlarmDisplayActivityModule
import com.example.tim.communityclock.ui.main.MainModule
import com.example.tim.communityclock.ui.main.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [AlarmDisplayActivityModule::class])
    internal abstract fun bindAlarmDisplayActivity(): AlarmDisplayActivity
}