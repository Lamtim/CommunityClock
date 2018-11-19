package com.example.tim.communityclock.ui.alarmdisplay

import com.example.tim.communityclock.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class AlarmDisplayActivityModule {

    @Provides
    fun provideAlarmDisplayViewModel(): AlarmDisplayViewModel {
        return AlarmDisplayViewModel()
    }

}