package com.turo.turodogclock;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by YQ950209 on 2016/9/12.
 */
public class TimeView extends LinearLayout{

    private TextView tvTime;


    /**
     * 被代码调用
     * @param context
     */
    public TimeView(Context context) {
        super(context);
    }


    /**
     * 被XML初始化器使用
     * @param context
     * @param attrs
     */
    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 初始化时可指定Style
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public TimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 在初始化完成后要做的事
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        tvTime = (TextView) findViewById(R.id.tvTime);
        tvTime.setText("Hello");

        timerHandler.sendEmptyMessage(0);//给handler发送消息
    }

    /**
     * 当tvtime可见性改变时，tvTime做出相应改变
     * @param changedView
     * @param visibility
     */
    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);

        if (visibility == View.VISIBLE){
            timerHandler.sendEmptyMessage(0);//可见时刷新时间
        }else{
            timerHandler.removeMessages(0);//不可见时把所有消息移除掉
        }
    }

    /**
     * 刷新时间
     */
    private void refreshTime(){
        Calendar c = Calendar.getInstance();

        tvTime.setText(String.format("%d:%d:%d",c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),c.get(Calendar.SECOND)));
    }

    /**
     * 用于更新主UI
     */
    private Handler timerHandler = new Handler(){

        public void handleMessage(android.os.Message msg){
            refreshTime();

            if (getVisibility()== View.VISIBLE){
                timerHandler.sendEmptyMessageDelayed(0,1000);//每隔一千毫秒执行一次handleMessage
            }
        }
    };

}
