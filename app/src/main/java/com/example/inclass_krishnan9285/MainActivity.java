package com.example.inclass_krishnan9285;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.inclass_krishnan9285.InClass01.InClass01;
import com.example.inclass_krishnan9285.InClass02.InClass02;
import com.example.inclass_krishnan9285.InClass03.InClass03;
import com.example.inclass_krishnan9285.InClass04.InClass04;
import com.example.inclass_krishnan9285.InClass05.InClass05;
import com.example.inclass_krishnan9285.InClass06.InClass06;
import com.example.inclass_krishnan9285.Practice.PracticeActivity;

public class MainActivity extends AppCompatActivity {
    private Button buttonPractice;
    private Button buttonInClass01;

    private Button buttonInClass02;
    private Button buttonInClass03;
    private Button buttonInClass04;
    private Button buttonInClass05;
    private Button buttonInClass06;
    private Button buttonInClass08;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPractice = findViewById(R.id.button);
        buttonInClass01 = findViewById(R.id.buttonInClass01);
        buttonInClass02 = findViewById(R.id.buttonInClass02);
        buttonInClass03 = findViewById(R.id.buttonInClass03);
        buttonInClass04 = findViewById(R.id.buttonInClass04);
        buttonInClass05 = findViewById(R.id.buttonInClass05);
        buttonInClass06 = findViewById(R.id.buttonInClass06);
        buttonInClass08 = findViewById(R.id.buttonInClass08);



        buttonPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toPracticeActivity = new Intent(MainActivity.this,
                        PracticeActivity.class);

                startActivity(toPracticeActivity);
            }
        });

        buttonInClass01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass01 = new Intent(MainActivity.this, InClass01.class);

                startActivity(toInClass01);
            }
        });

        buttonInClass02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass02 = new Intent(MainActivity.this, InClass02.class);

                startActivity(toInClass02);
            }
        });

        buttonInClass03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass03 = new Intent(MainActivity.this, InClass03.class);

                startActivity(toInClass03);
            }
        });

        buttonInClass04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass04 = new Intent(MainActivity.this, InClass04.class);

                startActivity(toInClass04);
            }
        });

        buttonInClass05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass05 = new Intent(MainActivity.this, InClass05.class);

                startActivity(toInClass05);
            }
        });

        buttonInClass06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass06 = new Intent(MainActivity.this, InClass06.class);

                startActivity(toInClass06);
            }
        });

        buttonInClass08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass08 = new Intent(MainActivity.this, InClass08.class);

                startActivity(toInClass08);
            }
        });

    }
}