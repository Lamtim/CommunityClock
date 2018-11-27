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
    var id: Long = 0

    //Voir si le format de l'heure est le meilleur, Long, Calendar, Time, etc ..
    //Reflechir si ca vaut le coup de stocker la différence de temps jusqu'à la sonnerie

    fun formatTime(): String? {
        return DateFormat.getTimeInstance(DateFormat.SHORT).format(time)
    }
}