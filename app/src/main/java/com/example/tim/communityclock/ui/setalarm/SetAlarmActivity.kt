package com.example.tim.communityclock.ui.setalarm

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.example.tim.communityclock.R
import com.example.tim.communityclock.data.model.api.Message
import com.example.tim.communityclock.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_set_alarm.*
import java.sql.Time
import java.util.*
import javax.inject.Inject

class SetAlarmActivity : BaseActivity(), SetAlarmInteractor {

    companion object {
        const val RECORD_REQUEST_CODE = 111
    }

    @Inject
    lateinit var setAlarmViewModel: SetAlarmViewModel
    var hours: Long = 0
    var minutes: Long = 0

    @RequiresApi(Build.VERSION_CODES.M)
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

        setAlarmViewModel.setInteractor(this)


        tv_test.text = "off"
        tp_alarm.setOnTimeChangedListener { tp_alarm, hourOfDay, minute ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setAlarmViewModel.updateLeftTime(tp_alarm.hour, tp_alarm.minute)
            } else {
                setAlarmViewModel.updateLeftTime(tp_alarm.currentHour, tp_alarm.currentMinute)
            }
        }
        button_set.setOnClickListener {
            setupPermissions()
        }
    }

    fun updateLeftTime(hourLeft: Long, minuteLeft: Long) {
        hours = hourLeft
        minutes = minuteLeft
        tv_test.text = "Il reste $hourLeft heure et $minuteLeft minutes"
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            RECORD_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.i("REJECTED", "Permission has been denied by user")
                } else {
                    setAlarm()
                }
            }
        }
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    RECORD_REQUEST_CODE)
        } else {
            setAlarm()
        }
    }

    private fun setAlarm() {
        val calendar = Calendar.getInstance()
        val time = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Time(tp_alarm.hour, tp_alarm.minute, 0)
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        Log.e("setAlarm", "setalarm")
        setAlarmViewModel.setNewAlarm("Salut", "")
    }

    override fun alarmRegistered(message: Message) {
        Toast.makeText(this, message.id, Toast.LENGTH_LONG).show()
        onBackPressed()
    }

}
