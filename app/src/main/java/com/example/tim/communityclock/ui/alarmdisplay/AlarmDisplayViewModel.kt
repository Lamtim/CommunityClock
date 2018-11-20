package com.example.tim.communityclock.ui.alarmdisplay

import com.example.tim.communityclock.ui.base.BaseViewModel
import javax.inject.Inject

class AlarmDisplayViewModel @Inject constructor(): BaseViewModel<AlarmDisplayInteractor>() {

    fun getSong():String{
       return "https://www.youtube.com/watch?v=daMvESm6GNs"
    }

}
