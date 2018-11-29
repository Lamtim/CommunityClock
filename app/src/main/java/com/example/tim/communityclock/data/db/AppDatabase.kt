package com.example.tim.communityclock.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.tim.communityclock.data.db.AlarmDao
import com.example.tim.communityclock.data.model.Alarm


@Database(entities = [Alarm::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}