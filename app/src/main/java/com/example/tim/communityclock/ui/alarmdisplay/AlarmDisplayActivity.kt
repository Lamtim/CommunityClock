package com.example.tim.communityclock.ui.alarmdisplay

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.tim.communityclock.R
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.design.button.MaterialButton
import android.view.Window
import com.example.tim.communityclock.services.AlarmService
import java.util.*
import android.view.WindowManager
import android.widget.TextView
import java.text.SimpleDateFormat
import android.media.MediaPlayer
import android.util.Log
import com.example.tim.communityclock.data.model.Alarm
import com.example.tim.communityclock.ui.base.BaseActivity
import com.example.tim.communityclock.utils.DateUtils
import kotlinx.android.synthetic.main.activity_lock_screen.*
import org.jetbrains.anko.doAsyncResult
import javax.inject.Inject


class AlarmDisplayActivity : BaseActivity(), AlarmDisplayInteractor {

    companion object {
        const val EXTRA_ALARM = "ALARM_DISPALLY_ACTIVITY"
    }

    @set:Inject
    lateinit var mAlarmDisplayViewModel: AlarmDisplayViewModel

    private var mPlayer: MediaPlayer? = null

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_ACTION_BAR)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON)
        actionBar?.hide()
        setContentView(R.layout.activity_lock_screen)

        findViewById<MaterialButton>(R.id.report).setOnClickListener {
            snoozeRing()
        }

        findViewById<MaterialButton>(R.id.close).setOnClickListener {
            closeRing()
        }

        mPlayer = MediaPlayer.create(this, mAlarmDisplayViewModel.getSong())
        mPlayer!!.start()
        mPlayer!!.setOnCompletionListener {
            mPlayer!!.start()
        }

        hour.text = DateUtils.getDateNowToString(SimpleDateFormat("HH:mm"))

        mAlarmDisplayViewModel.getAlarm(intent.getLongExtra(EXTRA_ALARM,0)).observe(this,
                android.arch.lifecycle.Observer { alarm ->
                    message.text = alarm!!.messageDisplayed
                })

    }

    override fun closeRing() {
        finish()
    }

    override fun snoozeRing() {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.SECOND, 5)

        val intent = Intent(this, AlarmService::class.java)
        val pintent = PendingIntent.getService(this, 0, intent, 0)
        val alarm = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, 60000, pintent)
    }

    override fun onDestroy() {
        mPlayer!!.release()
        mPlayer = null
        super.onDestroy()
    }
}