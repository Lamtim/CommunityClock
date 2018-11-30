package com.example.tim.communityclock.services

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import com.example.tim.communityclock.ui.alarmdisplay.AlarmDisplayActivity


class AlarmReceiver : JobService() {

    companion object {
        const val EXTRA_ALARM_ID = "james.alarmio.EXTRA_ALARM_ID"
    }


    override fun onStartJob(params: JobParameters): Boolean {
        val dialogIntent = Intent(this, AlarmDisplayActivity::class.java)
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(dialogIntent)
        return false
    }

    override fun onStopJob(params: JobParameters): Boolean {

        return false
    }

    

}