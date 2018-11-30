package com.example.tim.communityclock.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.example.tim.communityclock.R
import com.example.tim.communityclock.ViewModelProviderFactory
import com.example.tim.communityclock.ui.base.BaseActivity
import com.example.tim.communityclock.ui.setalarm.SetAlarmActivity
import kotlinx.android.synthetic.main.activity_main.*

import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var mViewModelProviderFactory: ViewModelProviderFactory
    var mAlarmRV: RecyclerView? = null
    var mAlarmAdapter: AlarmAdapter? = null
    lateinit var mMainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainViewModel = ViewModelProviders.of(this, mViewModelProviderFactory).get(MainViewModel::class.java)
        // mMainViewModel.deleteAll();
        getAlarms()
        bindViews()
        initRecyclerView()

    }

    private fun getAlarms() {
        mMainViewModel.alarms!!.observe(this,
                android.arch.lifecycle.Observer { alarms ->
                    mAlarmAdapter!!.setData(alarms)
                    mAlarmAdapter!!.notifyDataSetChanged()
                })
    }

    private fun bindViews() {
        mAlarmRV = findViewById(R.id.rv_alarm)

        fab_set_alarm.setOnClickListener {
            val intent = Intent(this, SetAlarmActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mAlarmRV!!.layoutManager = linearLayoutManager
        mAlarmRV!!.setHasFixedSize(true)
        mAlarmAdapter = AlarmAdapter()
        mAlarmRV!!.adapter = mAlarmAdapter
    }

}
