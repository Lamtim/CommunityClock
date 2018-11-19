package com.example.tim.communityclock.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tim.communityclock.R;
import com.example.tim.communityclock.ViewModelProviderFactory;
import com.example.tim.communityclock.data.model.db.Alarm;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelProviderFactory mViewModelProviderFactory;
    private RecyclerView mAlarmRV;
    private AlarmAdapter mAlarmAdapter;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainViewModel = ViewModelProviders.of(this, mViewModelProviderFactory).get(MainViewModel.class);
        getAlarms();
        bindViews();
        initRecyclerView();

    }

    private void getAlarms() {
        mMainViewModel.getAlarms().observe(this, alarms ->
                mAlarmAdapter.setData(alarms));
    }

    private void bindViews() {
        mAlarmRV = findViewById(R.id.rv_alarm);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAlarmRV.setLayoutManager(linearLayoutManager);
        mAlarmRV.setHasFixedSize(true);
        mAlarmAdapter = new AlarmAdapter(this);
        mAlarmRV.setAdapter(mAlarmAdapter);
    }
}
