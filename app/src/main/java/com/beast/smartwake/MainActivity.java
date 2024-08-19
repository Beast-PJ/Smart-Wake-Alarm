package com.beast.smartwake;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton alarmBtn, fabMenu;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmBtn = findViewById(R.id.setAlarmButton);
        fabMenu = findViewById(R.id.fabMenu);

        // Request permissions
        requestPermissions();

        alarmBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
            startActivity(intent);
        });

        fabMenu.setOnClickListener(v -> showBottomSheetDialog());

        // Initialize Bottom Sheet
        initBottomSheet();
    }

    private void initBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        View sheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_menu, null);
        bottomSheetDialog.setContentView(sheetView);

        // Handle Bottom Sheet item clicks
        TextView navItem1 = sheetView.findViewById(R.id.nav_item_1);
        TextView navItem2 = sheetView.findViewById(R.id.nav_item_2);
        TextView navItem3 = sheetView.findViewById(R.id.nav_item_3);
        TextView navItem4 = sheetView.findViewById(R.id.nav_item_4);

        navItem1.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            // Launch Alarm
            Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
            startActivity(intent);
        });

        navItem2.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            // Launch Stopwatch Activity
            Toast.makeText(MainActivity.this, "Stopwatch clicked", Toast.LENGTH_SHORT).show();
            // Intent intent = new Intent(MainActivity.this, StopwatchActivity.class);
            // startActivity(intent);
        });

        navItem3.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            // Launch Timer Activity
            Toast.makeText(MainActivity.this, "Timer clicked", Toast.LENGTH_SHORT).show();
            // Intent intent = new Intent(MainActivity.this, TimerActivity.class);
            // startActivity(intent);
        });

        navItem4.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            // Launch Settings Activity
            Toast.makeText(MainActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
            // Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            // startActivity(intent);
        });
    }

    private void showBottomSheetDialog() {
        bottomSheetDialog.show();
    }

    private void requestPermissions() {
        MultiplePermissionsListener snackbarMultiplePermissionsListener =
                SnackbarOnAnyDeniedMultiplePermissionsListener.Builder
                        .with(this.getCurrentFocus(), "All permissions are needed for this app to function properly")
                        .withOpenSettingsButton("Settings")
                        .build();

        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.VIBRATE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.USE_FULL_SCREEN_INTENT
                )
                .withListener(snackbarMultiplePermissionsListener)
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
