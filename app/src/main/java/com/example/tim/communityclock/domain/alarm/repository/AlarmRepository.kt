package com.example.tim.communityclock.domain.alarm.repository

import android.arch.lifecycle.LiveData
import com.example.tim.communityclock.data.model.Alarm

interface AlarmRepository {
    fun getAlarms(): LiveData<List<Alarm>>

    fun insertAlarm(alarm: Alarm)

    fun updateAlarm(alarm: Alarm)

    fun deleteAll()
}