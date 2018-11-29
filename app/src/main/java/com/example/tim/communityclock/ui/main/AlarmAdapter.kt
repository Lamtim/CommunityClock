package com.example.tim.communityclock.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.tim.communityclock.R
import com.example.tim.communityclock.data.model.Alarm

class AlarmAdapter : RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {

    private var mAlarms: List<Alarm>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AlarmAdapter.AlarmViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
                R.layout.item_alarm, viewGroup, false)
        return AlarmViewHolder(view)
    }

    override fun onBindViewHolder(alarmViewHolder: AlarmAdapter.AlarmViewHolder, i: Int) {
        alarmViewHolder.bind(mAlarms!![i])
    }

    override fun getItemCount(): Int {
        return if (mAlarms != null)
            mAlarms!!.size
        else
            0
    }

    fun setData(newAlarms: List<Alarm>?) {
        this.mAlarms = newAlarms
    }

    class AlarmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var time: TextView = itemView.findViewById(R.id.tv_time)

        fun bind(alarm: Alarm?) {
            if (alarm != null) {
                time.text = alarm.formatTime()
            }
        }
    }
}
