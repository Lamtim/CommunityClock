package com.example.tim.communityclock.data.local

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.tim.communityclock.data.model.db.Alarm
import com.example.tim.communityclock.data.model.db.AlarmDao
import com.example.tim.communityclock.domain.alarm.repository.AlarmRepository
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(private val alarmDao: AlarmDao) : AlarmRepository {
    override fun deleteAll() {
        doAsync { alarmDao.deleteAll() }

    }

    override fun insertAlarm(alarm: Alarm) {
        alarmDao.insertAlarm(alarm)
    }

    override fun getAlarms(): LiveData<List<Alarm>> {
        return alarmDao.getAlarms()
    }

}
