package com.example.tim.communityclock.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.tim.communityclock.ViewModelProviderFactory
import com.example.tim.communityclock.ui.alarmdisplay.AlarmDisplayViewModel
import com.example.tim.communityclock.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AlarmDisplayViewModel::class)
    abstract fun bindAlarmDisplayViewModel(alarmDisplayViewModel: AlarmDisplayViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlarmDisplayViewModel::class)
    abstract fun bindAlarmDisplayViewModel(alarmDisplayViewModel: AlarmDisplayViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}