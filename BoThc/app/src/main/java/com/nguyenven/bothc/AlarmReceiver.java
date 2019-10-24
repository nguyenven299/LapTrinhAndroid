package com.nguyenven.bothc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Tôi Trong Receiver", "Xin Chào");
        String chuoi_string = intent.getExtras().getString("extra");
        Log.e("Ban Truyen Key" , chuoi_string);
        Intent myIntent = new Intent(context,Music.class);
        myIntent.putExtra("extra",chuoi_string);
        context.startService(myIntent); // yeu cau thuc hien cua context bang services

    }
}
