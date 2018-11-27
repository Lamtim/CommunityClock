package com.example.tim.communityclock.ui.setalarm

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.customview.R.id.time
import android.util.Log
import com.example.tim.communityclock.R
import com.example.tim.communityclock.data.model.db.Alarm
import com.example.tim.communityclock.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_set_alarm.*
import java.sql.Time
import java.util.*
import javax.inject.Inject

class SetAlarmActivity : BaseActivity() {

    @Inject
    lateinit var setAlarmViewModel: SetAlarmViewModel
    var hours: Long = 0
    var minutes: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm)

        setAlarmViewModel.hourLeft!!.observe(this,
                android.arch.lifecycle.Observer { hours ->
                    if (hours != null) {
                        updateLeftTime(hours, minutes)
                    }
                })
        setAlarmViewModel.minuteLeft!!.observe(this,
                android.arch.lifecycle.Observer { minutes ->
                    if (minutes != null) {
                        updateLeftTime(hours, minutes)
                    }
                }
        )
        setAlarmViewModel.alarmCreated!!.observe(this,
                android.arch.lifecycle.Observer { alarm ->
                    if (alarm != null) {
                        tv_display_ring.text = alarm.formatTime()
                    }
                }
        )


        tv_test.text = "off"
        tp_alarm.setOnTimeChangedListener { tp_alarm, hourOfDay, minute ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setAlarmViewModel.updateLeftTime(tp_alarm.hour, tp_alarm.minute)
            } else {
                setAlarmViewModel.updateLeftTime(tp_alarm.currentHour, tp_alarm.currentMinute)
            }
        }
        button_set.setOnClickListener {
            val calendar = Calendar.getInstance()
            val time = Time(tp_alarm.hour, tp_alarm.minute, 0)
        }
    }

    fun updateLeftTime(hourLeft: Long, minuteLeft: Long) {
        hours = hourLeft
        minutes = minuteLeft
        tv_test.text = "Il reste $hourLeft heure et $minuteLeft minutes"
    }
}
