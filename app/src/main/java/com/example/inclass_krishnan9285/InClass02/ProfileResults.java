package com.example.inclass_krishnan9285.InClass02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inclass_krishnan9285.R;

public class ProfileResults extends AppCompatActivity {

    private TextView nameResult;
    private TextView emailResult;
    private TextView useResult;
    private TextView iAm;
    private ImageView avatarResult;
    private ImageView moodResult;
    private TextView nameText;
    private TextView emailText;
    private TextView useText;
    private TextView amText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_results);

        setTitle("Display Activity");

        nameResult = findViewById(R.id.nameResult);
        emailResult = findViewById(R.id.emailResult);
        useResult = findViewById(R.id.useResult);
        iAm = findViewById(R.id.iAm);
        avatarResult = findViewById(R.id.avatarResult);
        moodResult = findViewById(R.id.moodResult);
        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        useText = findViewById(R.id.useText);
        amText = findViewById(R.id.amText);


        Intent getResult = getIntent();
        Result profileResult = getResult.getParcelableExtra("Example Result");
        String name = profileResult.getEditTextName();
        String email = profileResult.getEditTextEmail();
        String device = profileResult.getDevice();
        String mood = profileResult.getMood();
        int avatarID = profileResult.getAvatarImageResource();
        int moodID = profileResult.getMoodImageResource();

        nameText.setText(name);
        emailText.setText(email);
        useText.setText(device + "!");
        amText.setText(mood + "!");

        avatarResult.setImageResource(avatarID);
        avatarResult = findViewById(avatarID);

        moodResult.setImageResource(moodID);
        moodResult = findViewById(moodID);
    }
}