package com.example.inclass_krishnan9285;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.inclass_krishnan9285.R;


public class InClass02 extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextEmail;
    private ImageView selectAvatar;
    private TextView iUse;
    private RadioGroup useGroup;
    private RadioButton android;
    private RadioButton iOS;
    private TextView currentMood;
    private TextView currentMoodDisplay;
    private SeekBar seekBarMood;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class02);

        setTitle("Edit Profile Activity");

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        selectAvatar = findViewById(R.id.select_avatar);
        iUse = findViewById(R.id.iUse);
        useGroup = findViewById(R.id.useGroup);
        android = findViewById(R.id.android);
        iOS = findViewById(R.id.iOS);
        currentMood = findViewById(R.id.currentMood);
        currentMoodDisplay = findViewById(R.id.currentMoodDisplay);
        seekBarMood = findViewById(R.id.seekBarMood);
        submit = findViewById(R.id.submit);

        ActivityResultLauncher<Intent> startActivityForResult =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                if (result.getResultCode() == RESULT_OK) {
                                    ImageView avatar = (ImageView) result.getData().getParcelableExtra("toMain");
                                    selectAvatar.setImageDrawable(avatar.getDrawable());

                                }
                            }


                        });

        selectAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSelectAvatar = new Intent(InClass02.this, SelectAvatar.class);
                startActivityForResult.launch(intentSelectAvatar);


            }
        });



    }
}