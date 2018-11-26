package com.example.tim.communityclock.data.model.db

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.*

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarm")
    fun getAlarms() : LiveData<List <Alarm>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertAlarm(alarm: Alarm)

    @Update
    fun updateAlarm(alarm: Alarm)

    @Delete
    fun deleteAlarm(alarm: Alarm)

    @Query("DELETE FROM alarm")
    fun deleteAll()
}