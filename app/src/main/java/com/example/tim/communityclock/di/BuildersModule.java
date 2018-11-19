package com.example.tim.communityclock.di;

import com.example.tim.communityclock.ui.alarmdisplay.AlarmDisplayActivity;
import com.example.tim.communityclock.ui.alarmdisplay.AlarmDisplayActivityModule;
import com.example.tim.communityclock.ui.main.MainModule;
import com.example.tim.communityclock.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = AlarmDisplayActivityModule.class)
    abstract AlarmDisplayActivity bindAlarmDisplayActivity();
}