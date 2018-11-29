package com.example.tim.communityclock.di

import android.arch.persistence.room.Room
import android.content.Context
import com.example.tim.communityclock.data.model.db.AlarmDao
import com.example.tim.communityclock.data.model.db.AppDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    //Firebase Module
    @Provides
    @Singleton
    fun providesFirebaseFirestore() = FirebaseFirestore.getInstance()

    //Firebase Storage
    @Provides
    @Singleton
    fun providesFirebaseStorage() = FirebaseStorage.getInstance()

    //Room Module
    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "communityclock.db")
                .fallbackToDestructiveMigration().build()
    }

    /**
     * Dao Providers
     */

    @Singleton
    @Provides
    fun provideAlarmDao(db: AppDatabase): AlarmDao {
        return db.alarmDao()
    }

}