package com.example.tim.communityclock.ui.alarmdisplay

import com.example.tim.communityclock.ViewModelProviderFactory
import com.example.tim.communityclock.domain.song.usecase.GetOneSongUseCase
import dagger.Module
import dagger.Provides

@Module
class AlarmDisplayActivityModule {

    @Provides
    fun provideAlarmDisplayViewModel(getOneSongUseCase: GetOneSongUseCase): AlarmDisplayViewModel {
        return AlarmDisplayViewModel(getOneSongUseCase)
    }

}