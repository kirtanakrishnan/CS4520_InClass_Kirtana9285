package com.example.inclass_krishnan9285.InClass04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.inclass_krishnan9285.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Kirtana Krishnan
Assignment #4
 */
public class InClass04 extends AppCompatActivity {

    private TextView selectComplexity, complexity, minimum, maximum, average, maxResult,
    minResult, avgResult;
    private SeekBar seekBarComplexity;
    private Button generateNumber;
    private ProgressBar progressBar;
    private ExecutorService threadPool;
    private Handler messageQueue;

    public int limit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class04);

        setTitle("Number Generator");

        selectComplexity = findViewById(R.id.selectComplexity);
        complexity = findViewById(R.id.complexity);
        minimum = findViewById(R.id.minimum);
        maximum = findViewById(R.id.maximum);
        average = findViewById(R.id.average);
        maxResult = findViewById(R.id.maxResult);
        minResult = findViewById(R.id.minResult);
        avgResult = findViewById(R.id.avgResult);
        seekBarComplexity = findViewById(R.id.seekBarComplexity);
        generateNumber = findViewById(R.id.generateNumber);
        progressBar = findViewById(R.id.progressBar);

        threadPool = Executors.newFixedThreadPool(1);

        limit = 1;
        complexity.setText("1 time");
        seekBarComplexity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b == true) {
                    if (i == 0) {
                        complexity.setText((i + 1) + " time");

                    } else {
                        complexity.setText((i + 1) + " times");
                    }
                    limit = i + 1;

                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        messageQueue = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                Bundle receivedData = message.getData();
                double min = receivedData.getDouble(HeavyWork.KEY_MIN);
                minResult.setText(min + "");
                double max = receivedData.getDouble(HeavyWork.KEY_MAX);
                maxResult.setText(max + "");
                double avg = receivedData.getDouble(HeavyWork.KEY_AVG);
                avgResult.setText(avg + "");


                int progress = (int) receivedData.getDouble(HeavyWork.KEY_PROGRESS);
                progressBar.setProgress(progress);

                return true;
            }
        });

        generateNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                threadPool.execute(new HeavyWork(limit, messageQueue));
            }
        });
    }
}