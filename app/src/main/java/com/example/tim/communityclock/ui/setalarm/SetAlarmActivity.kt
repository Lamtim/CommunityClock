package com.example.tim.communityclock.ui.setalarm

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.customview.R.id.time
import android.util.Log
import com.example.tim.communityclock.R
import com.example.tim.communityclock.data.model.db.Alarm
import kotlinx.android.synthetic.main.activity_set_alarm.*
import java.sql.Time
import java.util.*
import javax.inject.Inject

class SetAlarmActivity : AppCompatActivity() {

    lateinit var setAlarmViewModel:SetAlarmViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm)

        setAlarmViewModel.timeLeft!!.observe(this,
                android.arch.lifecycle.Observer{ lala ->
                    if (lala != null) {
                        updateLeftTime(lala,0)
                    }
        })


        tv_test.text = "off"
        tp_alarm.setOnTimeChangedListener { tp_alarm, hourOfDay, minute ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setAlarmViewModel.updateLeftTime(tp_alarm.hour, tp_alarm.minute)
            }else{
                setAlarmViewModel.updateLeftTime(tp_alarm.currentHour, tp_alarm.currentMinute)
            }
        }
        button_set.setOnClickListener {
            val calendar = Calendar.getInstance()
            val time = Time(tp_alarm.hour, tp_alarm.minute, 0)
        }
    }

    fun updateLeftTime(hourLeft: Long, minuteLeft:Long ){
        tv_test.text = "Il reste $hourLeft heure et $minuteLeft minutes"
    }
}
