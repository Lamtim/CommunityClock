package com.example.tim.communityclock.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tim.communityclock.R;
import com.example.tim.communityclock.data.model.db.Alarm;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private List<Alarm> mAlarms;
    private Context context;

    @NonNull
    @Override
    public AlarmAdapter.AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_alarm, viewGroup, false);

        return new AlarmViewHolder(view);
    }

    AlarmAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmAdapter.AlarmViewHolder alarmViewHolder, int i) {
        alarmViewHolder.bind(mAlarms.get(i));
    }

    @Override
    public int getItemCount() {
        if (mAlarms != null)
            return mAlarms.size();
        else
            return 0;
    }

    public void setData(List<Alarm> newAlarms) {
        this.mAlarms = newAlarms;
    }

    class AlarmViewHolder extends RecyclerView.ViewHolder {

        TextView time;
        TextView message;

        AlarmViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.tv_time);
            message = itemView.findViewById(R.id.tv_message);
        }

        void bind(final Alarm alarm) {
            if (alarm != null) {
                time.setText(alarm.formatTime());
                message.setText(alarm.getMessageDisplayed());
            }
        }
    }
}
