package com.example.inclass_krishnan9285.InClass02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.inclass_krishnan9285.R;

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
                Avatar avatar = new Avatar(R.drawable.avatar_f_1);
                Intent toMain = new Intent();
                toMain.putExtra("Example Avatar", avatar);


                setResult(RESULT_OK, toMain);
                finish();

            }
        });

        avatarf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Avatar avatar = new Avatar(R.drawable.avatar_f_2);
                Intent toMain = new Intent();
                toMain.putExtra("Example Avatar", avatar);
                setResult(RESULT_OK, toMain);
                finish();

            }
        });

        avatarf3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Avatar avatar = new Avatar(R.drawable.avatar_f_3);
                Intent toMain = new Intent();
                toMain.putExtra("Example Avatar", avatar);
                setResult(RESULT_OK, toMain);
                finish();
            }
        });

        avatarm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Avatar avatar = new Avatar(R.drawable.avatar_m_1);
                Intent toMain = new Intent();
                toMain.putExtra("Example Avatar", avatar);
                setResult(RESULT_OK, toMain);
                finish();
            }
        });

        avatarm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Avatar avatar = new Avatar(R.drawable.avatar_m_2);
                Intent toMain = new Intent();
                toMain.putExtra("Example Avatar", avatar);
                setResult(RESULT_OK, toMain);
                finish();
            }
        });

        avatarm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Avatar avatar = new Avatar(R.drawable.avatar_m_3);
                Intent toMain = new Intent();
                toMain.putExtra("Example Avatar", avatar);
                setResult(RESULT_OK, toMain);
                finish();
            }
        });

    }
}