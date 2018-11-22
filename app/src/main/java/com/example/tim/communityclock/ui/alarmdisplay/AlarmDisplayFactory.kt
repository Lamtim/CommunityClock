package com.example.tim.communityclock.ui.alarmdisplay

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.tim.communityclock.domain.song.usecase.GetOneSongUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class AlarmDisplayFactory @Inject constructor(
        private val getOneSong: GetOneSongUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlarmDisplayViewModel::class.java)) {
            return AlarmDisplayViewModel(getOneSong) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}