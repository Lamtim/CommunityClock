package com.example.tim.communityclock.domain.alarm.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.tim.communityclock.data.model.db.Alarm

interface AlarmRepository {
    fun getAlarms(): LiveData<List<Alarm>>

    fun insertAlarm(alarm: Alarm)
}