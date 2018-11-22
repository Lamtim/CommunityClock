package com.example.tim.communityclock.ui.alarmdisplay

import com.example.tim.communityclock.R
import com.example.tim.communityclock.domain.message.usecase.GetOneMessageUseCase
import com.example.tim.communityclock.domain.song.usecase.GetOneSongUseCase
import com.example.tim.communityclock.ui.base.BaseViewModel
import javax.inject.Inject

class AlarmDisplayViewModel @Inject constructor(): BaseViewModel<AlarmDisplayInteractor>() {

    fun getSong():Int{
       return R.raw.alarm1
    }

}
