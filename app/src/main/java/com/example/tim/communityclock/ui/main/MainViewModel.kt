package com.example.tim.communityclock.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.tim.communityclock.data.repo.AlarmRepositoryImpl

import com.example.tim.communityclock.data.model.Alarm
import org.jetbrains.anko.doAsync

import java.util.Date
import javax.inject.Inject

class MainViewModel @Inject constructor(val alarmRepositoryImpl: AlarmRepositoryImpl) : ViewModel() {

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

    fun updateAlarm(alarm: Alarm) {
        doAsync { alarmRepositoryImpl.updateAlarm(alarm) }
    }

}
