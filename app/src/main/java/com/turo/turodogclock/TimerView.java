package com.turo.turodogclock;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by YQ950209 on 2016/9/14.
 */
public class TimerView extends LinearLayout {


    private Button btnStart, btnPause, btnResume, btnReset;
    private EditText etHour, etMin, etSec;
    public TimerView(Context context) {
        super(context);
    }

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        btnStart = (Button) findViewById(R.id.btnStart);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnResume = (Button) findViewById(R.id.btnResume);
        btnReset = (Button) findViewById(R.id.btnReset);

        etHour = (EditText) findViewById(R.id.etHour);
        etMin = (EditText) findViewById(R.id.etMin);
        etSec = (EditText) findViewById(R.id.etSec);


    }
}
