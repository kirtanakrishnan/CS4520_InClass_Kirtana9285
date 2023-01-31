package com.example.inclass_krishnan9285;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;

public class SelectAvatar extends AppCompatActivity {

    private ImageView avatarf1;
    private ImageView avatarf2;
    private ImageView avatarf3;
    private ImageView avatarm1;
    private ImageView avatarm2;
    private ImageView avatarm3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);

        setTitle("Select Avatar");

        avatarf1 = findViewById(R.id.avatarf1);
        avatarf2 = findViewById(R.id.avatarf2);
        avatarf3 = findViewById(R.id.avatarf3);
        avatarm1 = findViewById(R.id.avatarm1);
        avatarm2 = findViewById(R.id.avatarm2);
        avatarm3 = findViewById(R.id.avatarm3);


        avatarf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView avatar = avatarf1;
                Intent toMain = new Intent();
                toMain.putExtra("toMain", (Parcelable) avatar);
                setResult(RESULT_OK, toMain);
                finish();
            }
        });

        avatarf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView avatar = avatarf2;
                Intent toMain = new Intent();
                toMain.putExtra("toMain", (Parcelable) avatar);
                setResult(RESULT_OK, toMain);
                finish();

            }
        });

        avatarf3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView avatar = avatarf3;
                Intent toMain = new Intent();
                toMain.putExtra("toMain", (Parcelable) avatar);
                setResult(RESULT_OK, toMain);
                finish();

            }
        });

        avatarm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView avatar = avatarm1;
                Intent toMain = new Intent();
                toMain.putExtra("toMain", (Parcelable) avatar);
                setResult(RESULT_OK, toMain);
                finish();

            }
        });

        avatarm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView avatar = avatarm2;
                Intent toMain = new Intent();
                toMain.putExtra("toMain", (Parcelable) avatar);
                setResult(RESULT_OK, toMain);
                finish();

            }
        });

        avatarm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView avatar = avatarm3;
                Intent toMain = new Intent();
                toMain.putExtra("toMain", (Parcelable) avatar);
                setResult(RESULT_OK, toMain);
                finish();

            }
        });

    }
}