package com.beast.smartwake;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton alarm_btn;
    private TimePicker alarmTimePicker;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @SuppressLint("ScheduleExactAlarm")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarm_btn = findViewById(R.id.setAlarmButton);
        // Request permissions
        requestPermissions();
        alarm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
                startActivity(intent);
            }
        });

        // Alarm initialization
//        alarmTimePicker = findViewById(R.id.timePicker);
//        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarm_btn = findViewById(R.id.setAlarmButton);
//        alarm_btn.setOnClickListener(v -> {
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
//            calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());
//            calendar.set(Calendar.SECOND, 0);
//
//            Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
//            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
//
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//            Toast.makeText(MainActivity.this, "Alarm Set", Toast.LENGTH_SHORT).show();
//        });
    }

    private void requestPermissions() {
        MultiplePermissionsListener snackbarMultiplePermissionsListener =
                SnackbarOnAnyDeniedMultiplePermissionsListener.Builder
                        .with(getCurrentFocus(), "All permissions are needed for this app to function properly")
                        .withOpenSettingsButton("Settings")
                        .build();

        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.VIBRATE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.USE_FULL_SCREEN_INTENT
                )
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted!", Toast.LENGTH_SHORT).show();
                        }
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            Toast.makeText(getApplicationContext(), "Some permissions are permanently denied. Please enable them from settings.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .withErrorListener(error -> Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show())
                .onSameThread()
                .check();
    }

}
