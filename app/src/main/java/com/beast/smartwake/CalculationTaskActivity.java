package com.beast.smartwake;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalculationTaskActivity extends AppCompatActivity {

    private EditText num1EditText, num2EditText;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_task);

        num1EditText = findViewById(R.id.num1EditText);
        num2EditText = findViewById(R.id.num2EditText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation();
            }
        });
    }

    private void performCalculation() {
        try {
            int num1 = Integer.parseInt(num1EditText.getText().toString());
            int num2 = Integer.parseInt(num2EditText.getText().toString());
            int result = num1 + num2;  // Simple calculation

            resultTextView.setText("Result: " + result);

            // Once the task is completed, show a message and go back to the main alarm activity
            Toast.makeText(CalculationTaskActivity.this, "Calculation Complete! Alarm turned off.", Toast.LENGTH_SHORT).show();
            finish();  // Close this activity after task completion
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers!", Toast.LENGTH_SHORT).show();
        }
    }
}
