package com.example.tim.communityclock.ui.setalarm

import android.arch.lifecycle.MutableLiveData
import android.annotation.SuppressLint
import android.util.Log
import com.example.tim.communityclock.data.model.db.Alarm
import com.example.tim.communityclock.data.remote.api.MessageRepositoryImpl
import com.example.tim.communityclock.data.remote.api.SongRepositoryImpl
import com.example.tim.communityclock.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*
import javax.inject.Inject

class SetAlarmViewModel @Inject constructor(val repository: MessageRepositoryImpl,
                                            val repositorySong: SongRepositoryImpl) : BaseViewModel<SetAlarmInteractor>() {

    var hourLeft: MutableLiveData<Long> = MutableLiveData()
    var minuteLeft: MutableLiveData<Long> = MutableLiveData()
    var alarmCreated: MutableLiveData<Alarm> = MutableLiveData()


    fun updateLeftTime(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        val date: Date = calendar.time
        var timeDiff = (date.time - System.currentTimeMillis())
        val msPerHour = 1000 * 60 * 60
        if (timeDiff <= 0) {
            timeDiff += 24 * msPerHour
        }
        hourLeft.value = timeDiff / msPerHour
        minuteLeft.value = (timeDiff / (1000 * 60)) % 60

        calendar.clear()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.MILLISECOND, timeDiff.toInt())
        alarmCreated.value = Alarm(calendar.timeInMillis, "lala")
    }

    @SuppressLint("CheckResult")
    fun setNewAlarm(message: String, path: String) {
        Log.e("setAlarm","viewmoded")
        //repository.sendMessage(message).subscribe()
        //repositorySong.sendSong(File(path)).subscribe()
        repository.getOneMessage()
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(
                      {
                          repository.cancelRegistration()
                          //getInteractor()!!.alarmRegistered(it)
                      },
                      {
                          Log.d("FAILED", "FAILED")
                      }
                  )
    }


}