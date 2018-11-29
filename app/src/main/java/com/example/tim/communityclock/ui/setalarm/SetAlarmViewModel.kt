package com.example.tim.communityclock.ui.setalarm

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.tim.communityclock.data.local.AlarmRepositoryImpl
import com.example.tim.communityclock.data.model.api.Message
import com.example.tim.communityclock.data.model.db.Alarm
import com.example.tim.communityclock.data.remote.api.MessageRepositoryImpl
import com.example.tim.communityclock.data.remote.api.SongRepositoryImpl
import com.example.tim.communityclock.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.doAsync
import java.util.*
import javax.inject.Inject

class SetAlarmViewModel @Inject constructor(val messageRepository: MessageRepositoryImpl,
                                            val songRepository: SongRepositoryImpl,
                                            val alarmRepository: AlarmRepositoryImpl) : BaseViewModel<SetAlarmInteractor>() {

    var hourLeft: MutableLiveData<Long> = MutableLiveData()
    var minuteLeft: MutableLiveData<Long> = MutableLiveData()
    var alarmCreated: MutableLiveData<Alarm> = MutableLiveData()
    var calendar: Calendar? = null

    fun updateLeftTime(){
        calendar = Calendar.getInstance()
        calendar?.timeInMillis = System.currentTimeMillis()
        updateLeftTime(calendar!!.get(Calendar.HOUR_OF_DAY),calendar!!.get(Calendar.MINUTE))
    }
    fun updateLeftTime(hour: Int, minute: Int) {
        calendar = Calendar.getInstance()
        calendar?.timeInMillis = System.currentTimeMillis()
        calendar?.set(Calendar.HOUR_OF_DAY, hour)
        calendar?.set(Calendar.MINUTE, minute)
        calendar?.set(Calendar.SECOND, 0)

        val date: Date = calendar!!.time
        var timeDiff = (date.time - System.currentTimeMillis())
        val msPerHour = 1000 * 60 * 60
        if (timeDiff <= 0) {
            timeDiff += 24 * msPerHour
        }
        hourLeft.value = timeDiff / msPerHour
        minuteLeft.value = (timeDiff / (1000 * 60)) % 60

        calendar?.clear()
        calendar?.timeInMillis = System.currentTimeMillis()
        calendar?.add(Calendar.MILLISECOND, timeDiff.toInt())
        alarmCreated.value = Alarm(calendar!!.timeInMillis, null)
    }

    @SuppressLint("CheckResult")
    fun setNewAlarm(message: String, path: String) {
        if (calendar == null){
            updateLeftTime()
        }
        Log.e("setAlarm", "viewmoded")
        //messageRepository.sendMessage(message).subscribe()
        //songRepository.sendSong(File(path)).subscribe()
        messageRepository.getOneMessage()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            messageRepository.cancelRegistration()
                            //getInteractor()!!.alarmRegistered(it)
                        },
                        {
                            Log.d("FAILED", "FAILED")
                        }
                )
        doAsync { alarmRepository.insertAlarm(Alarm(calendar!!.timeInMillis, message))}
        getInteractor()!!.alarmRegistered(message = Message("1",message,""))
    }

}