package com.beast.smartwake;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AlarmTaskActivity.class);
//        i.putExtra("TASK_TYPE", intent.getIntExtra("TASK_TYPE", R.id.radio_calculation));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
