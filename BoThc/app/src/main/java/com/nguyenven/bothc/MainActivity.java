package com.nguyenven.bothc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnHG;
    Button btnDL;
    TextView txtHienThi;
    TimePicker timePicker;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmManager= (AlarmManager)getSystemService(ALARM_SERVICE); // bao thuc
                btnHG= (Button) findViewById(R.id.buttonHenGio);
        btnDL = (Button) findViewById(R.id.buttonDungLai);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        txtHienThi = (TextView) findViewById(R.id.textView);
        calendar = calendar.getInstance();

       final Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);

        btnHG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());

                int Gio = timePicker.getCurrentHour();
                int Phut = timePicker.getCurrentMinute();

                String string_gio = String.valueOf(Gio);
                String string_phut = String.valueOf(Phut);
                if (Gio > 12)
                {
                    string_gio = String.valueOf(Gio -12);
                }
                if(Phut <10)
                {
                    string_phut = "0"+ String.valueOf(Phut );
                }

                intent.putExtra("extra","on");
                Context context;
                pendingIntent = pendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                txtHienThi.setText("Giờ Bạn Đặt Là:"+ string_gio + ":" + string_phut);
            }
        });

        btnDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtHienThi.setText("Dừng Lại");
                alarmManager.cancel(pendingIntent);
                intent.putExtra("extra","off");
                sendBroadcast(intent);
            }
        });
    }
}
