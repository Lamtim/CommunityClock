package com.example.tim.communityclock.di

import com.example.tim.communityclock.ui.alarmdisplay.AlarmDisplayActivity
import com.example.tim.communityclock.ui.main.MainActivity
import com.example.tim.communityclock.ui.setalarm.SetAlarmActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun bindAlarmDisplayActivity(): AlarmDisplayActivity

    @ContributesAndroidInjector
    internal abstract fun bindSetAlarmActivity(): SetAlarmActivity
}