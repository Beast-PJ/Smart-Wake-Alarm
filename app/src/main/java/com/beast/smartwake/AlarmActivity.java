package com.beast.smartwake;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Button setAlarmButton;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private RadioGroup taskTypeGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        timePicker = findViewById(R.id.time_picker);
        setAlarmButton = findViewById(R.id.setAlarmButton);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        setAlarmButton.setOnClickListener(v -> setAlarm());

        taskTypeGroup.setOnCheckedChangeListener((group, checkedId) -> setAlarmButton.setEnabled(true));
    }

    @SuppressLint("ScheduleExactAlarm")
    private void setAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
        calendar.set(Calendar.MINUTE, timePicker.getMinute());
        calendar.set(Calendar.SECOND, 0);

        int selectedTaskType = taskTypeGroup.getCheckedRadioButtonId();
        Intent intent = new Intent(AlarmActivity.this, AlarmReceiver.class);
        intent.putExtra("TASK_TYPE", selectedTaskType);

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Snackbar.make(findViewById(android.R.id.content), "Alarm Set!", Snackbar.LENGTH_LONG)
                .setBackgroundTint(ContextCompat.getColor(this, R.color.teal_700))
                .show();

        finish();
    }
}
