package com.example.tim.communityclock.data.model.db

import java.text.DateFormat

class Alarm (val time: Long, val messageDisplayed: String){


    fun formatTime(): String? {
        return DateFormat.getTimeInstance(DateFormat.SHORT).format(time)
    }
}