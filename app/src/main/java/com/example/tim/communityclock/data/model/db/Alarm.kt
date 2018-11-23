package com.example.tim.communityclock.data.model.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.text.DateFormat

@Entity
class Alarm(
        var time: Long,
        var messageDisplayed: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = -1

    fun formatTime(): String? {
        return DateFormat.getTimeInstance(DateFormat.SHORT).format(time)
    }
}