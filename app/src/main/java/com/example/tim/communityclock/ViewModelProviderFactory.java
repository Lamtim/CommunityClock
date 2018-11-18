package com.example.tim.communityclock;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class ViewModelProviderFactory<V> implements ViewModelProvider.Factory {

    private V viewModel;

    ViewModelProviderFactory(V viewModel){
        this.viewModel = viewModel;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(viewModel.getClass())){
            return (T) viewModel;
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}
