package com.beast.smartwake;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class AlarmTaskActivity extends AppCompatActivity {

    private TextView taskTextView;
    private Button completeTaskButton;
    private int correctAnswer;
    private int selectedTaskType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_task);

        taskTextView = findViewById(R.id.taskTextView);
        completeTaskButton = findViewById(R.id.completeTaskButton);

        selectedTaskType = getIntent().getIntExtra("TASK_TYPE", R.id.radio_calculation);

        switch (selectedTaskType) {
            case R.id.radio_calculation:
                generateMathProblem();
                break;
            case R.id.radio_memory:
                startMemoryGame();
                break;
            case R.id.radio_scan:
                startScanTask();
                break;
        }

        completeTaskButton.setOnClickListener(v -> {
            if (verifyTask()) {
                Toast.makeText(this, "Task Completed! Alarm turned off.", Toast.LENGTH_SHORT).show();
                finish();  // Close the activity and turn off the alarm
            } else {
                Toast.makeText(this, "Incorrect answer! Try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateMathProblem() {
        Random random = new Random();
        int number1 = random.nextInt(10) + 1;
        int number2 = random.nextInt(10) + 1;
        correctAnswer = number1 + number2;
        taskTextView.setText("What is " + number1 + " + " + number2 + "?");
    }

    private void startMemoryGame() {
        // Placeholder: Implement memory game logic here
        taskTextView.setText("Memory Game: Remember the sequence of colors.");
        // Set up the correct answer or sequence for the memory game
        correctAnswer = 1; // Placeholder for correct verification
    }

    private void startScanTask() {
        // Placeholder: Implement scan logic here
        taskTextView.setText("Scan any object or barcode to turn off the alarm.");
        // Set up the correct answer or barcode for scanning
        correctAnswer = 1; // Placeholder for correct verification
    }

    private boolean verifyTask() {
        // Here you would check if the task is correctly completed.
        // For simplicity, we return true.
        return true; // Placeholder: Replace with actual verification logic
    }
}
