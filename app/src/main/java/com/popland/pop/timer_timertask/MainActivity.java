package com.popland.pop.timer_timertask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
Button btnStart, btnCancel;
CheckBox checkBox;
TextView tv;
Timer timer;
MyTimerTask myTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        tv = (TextView)findViewById(R.id.tv);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timer!=null)
                    timer.cancel();
                timer = new Timer();
                myTimerTask = new MyTimerTask();//schedule new task
                if(checkBox.isChecked())
                    timer.schedule(myTimerTask,1000);//do a single execution after 1s
                else
                    timer.schedule(myTimerTask,1000,2000);//start first execution after 1s. Then repeat it every 2s
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timer!=null) {
                    timer.cancel();
                    timer = null;
                }
            }
        });
    }

    class MyTimerTask extends TimerTask{//every task is a thread
        @Override
        public void run() {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MM:yyyy hh:mm:ss a");
            final String time = simpleDateFormat.format(calendar.getTime());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv.setText(time);
                }
            });
        }
    }
}
