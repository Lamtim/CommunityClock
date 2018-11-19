package com.example.tim.communityclock.ui.alarmdisplay

import com.example.tim.communityclock.ui.base.BaseViewModel

class AlarmDisplayViewModel(): BaseViewModel<AlarmDisplayInteractor>() {

    fun getSong():String{
       return "https://www.youtube.com/watch?v=daMvESm6GNs"
    }

}
