package com.example.tim.communityclock.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.tim.communityclock.data.model.db.Alarm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Alarm>> mAlarmsList;

    MainViewModel(){
    }

    private void createAlarmList(){
        mAlarmsList = new MutableLiveData<>();
        List<Alarm> list = new ArrayList<>();
        list.add(new Alarm(new Date().getTime(), "Bonne journée"));
        list.add(new Alarm(new Date().getTime(), "Bonne journée"));
        list.add(new Alarm(new Date().getTime(), "Bonne journée"));
        mAlarmsList.setValue(list);
    }

    public MutableLiveData<List<Alarm>> getAlarms() {
        if (mAlarmsList == null){
            createAlarmList();
        }
        return mAlarmsList;
    }
}
