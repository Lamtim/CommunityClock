package com.example.tim.communityclock.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.tim.communityclock.data.local.AlarmRepositoryImpl

import com.example.tim.communityclock.data.model.db.Alarm
import org.jetbrains.anko.doAsync

import java.util.ArrayList
import java.util.Date
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class MainViewModel @Inject constructor(val alarmRepositoryImpl: AlarmRepositoryImpl) : ViewModel() {

    val alarms: LiveData<List<Alarm>>?
        get() {
            return alarmRepositoryImpl.getAlarms()
        }

    fun deleteAll(){
        alarmRepositoryImpl.deleteAll()
    }

    fun addAlarm(){
        doAsync { alarmRepositoryImpl.insertAlarm(Alarm(Date().time, "Bonne journée")) }
    }

}
