package com.example.tim.communityclock.ui.setalarm

import com.example.tim.communityclock.data.model.api.Message

interface SetAlarmInteractor {

    fun alarmRegistered(message: Message)
}