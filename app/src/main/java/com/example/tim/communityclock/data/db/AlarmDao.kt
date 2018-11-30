package com.example.tim.communityclock.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.tim.communityclock.data.model.Alarm

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarm")
    fun getAlarms(): LiveData<List<Alarm>>

    @Query("SELECT * FROM alarm WHERE id=:id")
    fun getAlarm(id: Long): LiveData<Alarm>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlarm(alarm: Alarm)

    @Update
    fun updateAlarm(alarm: Alarm)

    @Delete
    fun deleteAlarm(alarm: Alarm)

    @Query("DELETE FROM alarm")
    fun deleteAll()
}