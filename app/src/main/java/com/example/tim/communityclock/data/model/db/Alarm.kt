package com.example.tim.communityclock.data.model.db

import java.text.DateFormat

class Alarm (val time: Long, val messageDisplayed: String){

    //Voir si le format de l'heure est le meilleur, Long, Calendar, Time, etc ..
    //Reflechir si ca vaut le coup de stocker la différence de temps jusqu'à la sonnerie

    fun formatTime(): String? {
        return DateFormat.getTimeInstance(DateFormat.SHORT).format(time)
    }
}