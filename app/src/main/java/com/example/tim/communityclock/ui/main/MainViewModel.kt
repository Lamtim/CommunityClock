package com.example.tim.communityclock.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.tim.communityclock.data.local.AlarmRepositoryImpl

import com.example.tim.communityclock.data.model.db.Alarm

import java.util.ArrayList
import java.util.Date
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class MainViewModel @Inject constructor(val alarmRepositoryImpl: AlarmRepositoryImpl) : ViewModel() {

    private var mAlarmsList: LiveData<List<Alarm>>? = null
    private var executorService: ExecutorService? = null

    val alarms: LiveData<List<Alarm>>?
        get() {
            if (mAlarmsList == null) {
                createAlarmList()
            }
            return mAlarmsList
        }

    private fun createAlarmList() {
        executorService = Executors.newSingleThreadExecutor()
        executorService!!.execute { alarmRepositoryImpl.insertAlarm(Alarm(Date().time, "Bonne journée"))}
        executorService = Executors.newSingleThreadExecutor()
        executorService!!.execute { alarmRepositoryImpl.insertAlarm(Alarm(Date().time, "Bonne journée"))}
        executorService = Executors.newSingleThreadExecutor()
        executorService!!.execute { alarmRepositoryImpl.insertAlarm(Alarm(Date().time, "Bonne journée"))}
        getAlarmList()
    }

    private fun getAlarmList(){
        mAlarmsList = alarmRepositoryImpl.getAlarms()
    }
}
