package com.turo.turodogclock;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Date;

/**
 * Created by YQ950209 on 2016/9/12.
 */
public class AlarmView extends LinearLayout{

    private Button btnAddAlarm;
    private ListView lvAlarmlist;
    private ArrayAdapter<AlarmData> adapter;

    public AlarmView(Context context) {
        super(context);
    }

    public AlarmView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlarmView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

        @Override
        protected void onFinishInflate() {
            super.onFinishInflate();

            btnAddAlarm = (Button) findViewById(R.id.btnAddAlarm);
            lvAlarmlist = (ListView) findViewById(R.id.lvAlarmList);

            adapter = new ArrayAdapter<AlarmView.AlarmData>(getContext(), android.R.layout.simple_list_item_1);
            lvAlarmlist.setAdapter(adapter);

            adapter.add(new AlarmData(System.currentTimeMillis()));

            btnAddAlarm.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    addAlarm();
                }
            });

        }

    private void addAlarm(){
        //TODO
    }

    private static class AlarmData{

        private Date date;
        private String timeLabel = "";
        private long time = 0;

        public AlarmData(long time){
            this.time = time;

            date = new Date(time);
            timeLabel = date.getHours()+":"+date.getMinutes();
        }

        public long getTime(){
            return time;
        }

        public String getTimeLabel(){
            return timeLabel;
        }

        @Override
        public String toString() {
            return getTimeLabel();
        }
    }

}
