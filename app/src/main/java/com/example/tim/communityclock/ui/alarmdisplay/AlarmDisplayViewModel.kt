package com.example.tim.communityclock.ui.alarmdisplay

import android.arch.lifecycle.LiveData
import com.example.tim.communityclock.R
import com.example.tim.communityclock.data.model.Alarm
import com.example.tim.communityclock.data.repo.AlarmRepositoryImpl
import com.example.tim.communityclock.ui.base.BaseViewModel
import javax.inject.Inject

class AlarmDisplayViewModel @Inject constructor(private val alarmRepositoryImpl: AlarmRepositoryImpl) : BaseViewModel<AlarmDisplayInteractor>() {


    fun getSong(): Int {
        return R.raw.alarm1
    }

    fun getAlarm(id: Long): LiveData<Alarm> {
        return alarmRepositoryImpl.getAlarm(id)
    }

}
