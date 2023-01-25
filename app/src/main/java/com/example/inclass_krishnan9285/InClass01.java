package com.example.inclass_krishnan9285;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
Kirtana Krishnan
Assignment #1
 */
public class InClass01 extends AppCompatActivity {

    Button calculateBMI;
    EditText weightInput;
    EditText feetInput;
    EditText inchesInput;

    TextView result;
    TextView result2;
    TextView calculateText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class01);

        setTitle("BMI Calculator");

        calculateBMI = findViewById(R.id.calculateBMI);
        weightInput = findViewById(R.id.weightInput);
        feetInput = findViewById(R.id.feetInput);
        inchesInput = findViewById(R.id.inchesInput);
        result = findViewById(R.id.result);
        result2 = findViewById(R.id.result2);
        calculateText = findViewById(R.id.calculateText);


        calculateBMI.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                calculateText.setText("");

                String weightInputString = weightInput.getText().toString().trim();
                String feetInputString = feetInput.getText().toString().trim();
                String inchesInputString = inchesInput.getText().toString().trim();

                if (weightInputString.equals("") ||
                        feetInputString.equals("") ||
                        inchesInputString.equals("") ||
                        (Double.parseDouble(weightInputString)) <= 0 ||
                        (Double.parseDouble(feetInputString)) < 0 ||
                        (Double.parseDouble(inchesInputString)) < 0) {

                    Toast.makeText(InClass01.this, "Invalid Inputs",
                            Toast.LENGTH_LONG).show();

                } else {

                    double calculateResult = ((Double.parseDouble(weightInputString)) /
                            (((Double.parseDouble(feetInputString) * 12) +
                                    (Double.parseDouble(inchesInputString)))

                                    * ((Double.parseDouble(feetInputString) * 12) +
                                    (Double.parseDouble(inchesInputString))))) * 703;

                    BigDecimal calculateResultBD = new BigDecimal(Double.toString(calculateResult));
                    calculateResultBD = calculateResultBD.setScale(1, RoundingMode.HALF_UP);
                    calculateResult = calculateResultBD.doubleValue();

                    result.setText("Your BMI: " + calculateResult);
                    if (calculateResult < 18.5) {
                        result2.setText("You are Underweight");
                    } else if (calculateResult >= 18.5 && calculateResult < 25) {
                        result2.setText("You are Normal weight");
                    } else if (calculateResult >= 25 && calculateResult < 30) {
                        result2.setText("You are Overweight");
                    } else if (calculateResult > 30) {
                        result2.setText("You are Obese");
                    }

                    Toast.makeText(InClass01.this, "BMI Calculated",
                            Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}