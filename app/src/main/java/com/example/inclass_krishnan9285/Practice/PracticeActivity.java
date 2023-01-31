package com.example.inclass_krishnan9285.Practice;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.inclass_krishnan9285.R;

public class PracticeActivity extends AppCompatActivity {

    Button button_log;
    Button button_toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        setTitle("Practice");
        button_log = findViewById(R.id.button2);

        button_log.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d(TAG, "Practice!Practice!!Practice!!!");
            }
        });

        button_toast = findViewById(R.id.button3);

        button_toast.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(PracticeActivity.this, "Now push to Github and Submit!",
                        Toast.LENGTH_LONG).show();

            }
        });
    }
}