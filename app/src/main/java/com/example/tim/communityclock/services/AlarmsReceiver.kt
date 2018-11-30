package com.example.tim.communityclock.services

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.tim.communityclock.data.model.Alarm
import com.example.tim.communityclock.ui.alarmdisplay.AlarmDisplayActivity

class AlarmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val ringer = Intent(context, AlarmDisplayActivity::class.java)
        ringer.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        ringer.putExtra(AlarmDisplayActivity.EXTRA_ALARM, intent.extras!![EXTRA_ALARM_ID] as Long)
        context.startActivity(ringer)
    }

    companion object {
        const val EXTRA_ALARM_MESSAGE = "EXTRA_ALARM_MESSAGE"
        const val EXTRA_ALARM_TIME = "EXTRA_ALARM_TIME"
        const val EXTRA_ALARM_ID = "EXTRA_ALARM_ID"
    }
}
