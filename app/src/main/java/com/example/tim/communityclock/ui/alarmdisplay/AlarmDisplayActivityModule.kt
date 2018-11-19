package com.example.tim.communityclock.ui.alarmdisplay

import dagger.Module
import dagger.Provides

@Module
class AlarmDisplayActivityModule {

    @Provides
    private fun provideAlarmDisplayViewModel(): AlarmDisplayActivity {
        return AlarmDisplayActivity()
    }

}