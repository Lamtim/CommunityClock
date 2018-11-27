package com.example.tim.communityclock.di

import android.content.Context
import com.example.tim.communityclock.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module (includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: App): Context {
        return app.applicationContext
    }

}