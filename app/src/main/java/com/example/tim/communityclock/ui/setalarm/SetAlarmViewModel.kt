package com.example.tim.communityclock.ui.setalarm

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.tim.communityclock.R.id.tv_test
import com.example.tim.communityclock.data.model.db.Alarm
import com.example.tim.communityclock.ui.base.BaseViewModel
import kotlinx.android.synthetic.main.activity_set_alarm.*
import java.util.*
import javax.inject.Inject

class SetAlarmViewModel @Inject constructor() : BaseViewModel<SetAlarmInteractor>() {

    var hourLeft: MutableLiveData<Long> = MutableLiveData()
    var minuteLeft: MutableLiveData<Long> = MutableLiveData()
    var alarmCreated: MutableLiveData<Alarm> = MutableLiveData()


    fun updateLeftTime(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        val date: Date = calendar.time
        var timeDiff = (date.time - System.currentTimeMillis())
        val msPerHour = 1000 * 60 * 60
        if (timeDiff <= 0) {
            timeDiff += 24 * msPerHour
        }
        hourLeft.value = timeDiff / msPerHour
        minuteLeft.value = (timeDiff / (1000 * 60)) % 60

        calendar.clear();
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.MILLISECOND, timeDiff.toInt())
        alarmCreated.value = Alarm(calendar.timeInMillis, "lala")
    }

    fun setNewAlarm() {
        //Stockage de l'alarm en sur Room
    }
}