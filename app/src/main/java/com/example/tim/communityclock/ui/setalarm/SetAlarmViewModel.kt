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

class SetAlarmViewModel @Inject constructor(): BaseViewModel<SetAlarmInteractor>() {

    var hourLeft : MutableLiveData<Long> = MutableLiveData()
    var minLeft : MutableLiveData<Long> = MutableLiveData()
    var timeLeft: MediatorLiveData<Long> = MediatorLiveData()



    fun updateLeftTime(hour: Int, minute: Int) {
        Log.v("TimePicker", "Hour $hour Minute $minute");
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        val date: Date = calendar.time
        Log.v("DatePicker", "" + date)
        var timeDiff = (date.time - System.currentTimeMillis())
        Log.v("TimeLeft", "" + timeDiff+ " = "+ date.time+" + "+System.currentTimeMillis())
        val msPerHour = 1000 * 60 * 60
        if (timeDiff > 0) {
            timeLeft.addSource(hourLeft) { value -> timeLeft.value = value}
            hourLeft.value = timeDiff / msPerHour
            minLeft.value = (timeDiff / (1000 * 60)) % 60
            Log.v("minLeft", "" + minLeft)
            //tv_test.text = "Il reste $hourLeft heure et $minLeft minutes"
        }else{
            timeDiff += 24*msPerHour
            hourLeft.value = timeDiff / msPerHour
            minLeft.value = (timeDiff / (1000 * 60)) % 60
            //tv_test.text = "Il reste $hourLeft heure et $minLeft minutes"
        }

        calendar.clear();
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.MILLISECOND, timeDiff.toInt())
        val alarm = Alarm(calendar.timeInMillis, "lala")
        //tv_display_ring.text = alarm.formatTime()
    }

    fun setNewAlarm(){

    }
}