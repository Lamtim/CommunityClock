package com.example.tim.communityclock.ui.main;

import android.arch.lifecycle.ViewModelProvider;

import com.example.tim.communityclock.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    ViewModelProviderFactory mainViewModelProvider(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    MainViewModel provideMainViewModel() {
        return new MainViewModel();
    }
}
