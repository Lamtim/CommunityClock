package com.example.tim.communityclock.di;

import com.example.tim.communityclock.MainActivity;
import com.example.tim.communityclock.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity bindMainActivity();
}