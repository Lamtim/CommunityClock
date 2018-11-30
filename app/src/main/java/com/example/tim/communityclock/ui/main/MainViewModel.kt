package com.example.tim.communityclock.ui.main

import android.app.AlarmManager
import android.app.PendingIntent
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.tim.communityclock.data.repo.AlarmRepositoryImpl

import com.example.tim.communityclock.data.model.Alarm
import com.example.tim.communityclock.services.AlarmsReceiver
import com.example.tim.communityclock.ui.alarmdisplay.AlarmDisplayActivity
import com.example.tim.communityclock.utils.DateUtils
import org.jetbrains.anko.doAsync
import java.util.*

import javax.inject.Inject

class MainViewModel @Inject constructor(val alarmRepositoryImpl: AlarmRepositoryImpl,
                                        val appContext: Context) : ViewModel() {

    val alarms: LiveData<List<Alarm>>?
        get() {
            return alarmRepositoryImpl.getAlarms()
        }

    fun deleteAll() {
        alarmRepositoryImpl.deleteAll()
    }

    fun addAlarm() {
        doAsync { alarmRepositoryImpl.insertAlarm(Alarm(Date().time, "Bonne journée")) }
    }

    fun enableAlarm(alarm: Alarm) {
        val alarmManager: AlarmManager = appContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val now = Calendar.getInstance()
        var next = Calendar.getInstance()
        val toModify = Calendar.getInstance()
        toModify.time  = Date(alarm.time)
        next.set(Calendar.HOUR_OF_DAY, toModify.get(Calendar.HOUR_OF_DAY))
        next.set(Calendar.MINUTE, toModify.get(Calendar.MINUTE))
        next.set(Calendar.SECOND, 0)
        if (now.after(next))
            next.add(Calendar.DATE, 1)

        Log.e("ALARM DATE", DateUtils.dateToString(next.time))
        val intent = Intent(appContext, AlarmsReceiver::class.java)
        intent.putExtra(AlarmsReceiver.EXTRA_ALARM_ID, alarm.id)

        // TEST
        next = Calendar.getInstance()
        next.timeInMillis = System.currentTimeMillis()
        next.add(Calendar.SECOND, 5)
        // END TEST

        alarmManager.set(AlarmManager.RTC_WAKEUP, next.timeInMillis, PendingIntent.getBroadcast(appContext, alarm.id.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT))
    }

    fun disableAlarm(alarm: Alarm) {
        val alarmManager: AlarmManager = appContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(appContext, AlarmsReceiver::class.java)
        intent.putExtra(AlarmsReceiver.EXTRA_ALARM_ID, alarm.id)
        alarmManager.cancel(PendingIntent.getBroadcast(appContext, alarm.id.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT))
    }

}
