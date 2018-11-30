package com.example.tim.communityclock.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.tim.communityclock.ui.alarmdisplay.AlarmDisplayActivity

class AlarmService : Service() {
    override fun onCreate() {
        super.onCreate()
        Log.e("Service", "Create")
        val dialogIntent = Intent(this, AlarmDisplayActivity::class.java)
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(dialogIntent)
        onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e("Service", "bind")
        return null
    }

    override fun onDestroy() {
        Log.e("Service", "destroyed")
        super.onDestroy()
    }

}