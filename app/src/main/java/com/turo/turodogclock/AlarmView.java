package com.turo.turodogclock;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.Calendar;

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
        //
        Calendar c = Calendar.getInstance();


        /**弹出时间选择对话框
        （para1:Context传入getContext（），
        para2：callback，
        para3：当前时间（时），
        para4：当前时间（分），
        para5：是否24小时制）*/
        new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);//设置时间
                calendar.set(Calendar.MINUTE,minute);//设置分钟

                Calendar currentTime = Calendar.getInstance();//当前时间

                if (calendar.getTimeInMillis()<=currentTime.getTimeInMillis()){
                    calendar.setTimeInMillis(calendar.getTimeInMillis()+24*60*60*1000);//向后推一天
                }
                adapter.add(new AlarmData(calendar.getTimeInMillis()));//添加到ListView中
            }
        },c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),true).show();

    }

    private static class AlarmData{

        private Calendar date;
        private String timeLabel = "";
        private long time = 0;

        public AlarmData(long time){
            this.time = time;

            date = Calendar.getInstance();
            date.setTimeInMillis(time);

            timeLabel = String.format("%d月%d日 %d:%d",
                    date.get(Calendar.MONTH)+1,
                    date.get(Calendar.DAY_OF_MONTH),
                    date.get(Calendar.HOUR_OF_DAY),
                    date.get(Calendar.MINUTE));
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
