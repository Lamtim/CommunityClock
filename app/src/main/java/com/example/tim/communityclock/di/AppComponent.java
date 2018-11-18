package com.example.tim.communityclock.di;


import com.example.tim.communityclock.App;
import com.example.tim.communityclock.MainModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        MainModule.class,
        BuildersModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(App application);
        AppComponent build();
    }
    void inject(App app);
}