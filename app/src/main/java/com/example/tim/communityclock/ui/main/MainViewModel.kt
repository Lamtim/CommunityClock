package com.example.tim.communityclock.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.example.tim.communityclock.data.model.db.Alarm

import java.util.ArrayList
import java.util.Date
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    private var mAlarmsList: MutableLiveData<List<Alarm>>? = null

    val alarms: MutableLiveData<List<Alarm>>?
        get() {
            if (mAlarmsList == null) {
                createAlarmList()
            }
            return mAlarmsList
        }

    private fun createAlarmList() {
        mAlarmsList = MutableLiveData()
        val list = ArrayList<Alarm>()
        list.add(Alarm(Date().time, "Bonne journée"))
        list.add(Alarm(Date().time, "Bonne journée"))
        list.add(Alarm(Date().time, "Bonne journée"))
        mAlarmsList!!.value = list
    }
}
