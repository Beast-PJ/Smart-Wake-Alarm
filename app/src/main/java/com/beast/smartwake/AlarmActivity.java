package com.beast.smartwake;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class AlarmActivity extends AppCompatActivity {

    private MaterialCardView calculationCard, memoryCard, scanCard, customTaskCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        // Initialize cards
        calculationCard = findViewById(R.id.icon_calculation);
        memoryCard = findViewById(R.id.icon_memory);
        scanCard = findViewById(R.id.icon_scan);
        customTaskCard = findViewById(R.id.icon_custom_task);

        // Set click listeners
        calculationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTask("calculation");
            }
        });

        memoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTask("memory");
            }
        });

        scanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTask("scan");
            }
        });

        customTaskCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTask("custom");
            }
        });

        // Handle "Set Alarm" button click (if necessary)
        findViewById(R.id.setAlarmButton).setOnClickListener(v -> {
            // Alarm setting functionality goes here
            Toast.makeText(AlarmActivity.this, "Alarm Set", Toast.LENGTH_SHORT).show();
        });
    }

    private void startTask(String task) {
        Intent intent;
        switch (task) {
            case "calculation":
                intent = new Intent(this, CalculationTaskActivity.class);
                break;
            case "memory":
                intent = new Intent(this, MemoryTaskActivity.class);
                break;
            case "scan":
                intent = new Intent(this, ScanTaskActivity.class);
                break;
            case "custom":
                intent = new Intent(this, CustomTaskActivity.class);
                break;
            default:
                return;
        }
        startActivity(intent);
    }
}
