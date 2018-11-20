package com.example.tim.communityclock.di

import android.arch.lifecycle.ViewModelProvider
import com.example.tim.communityclock.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}