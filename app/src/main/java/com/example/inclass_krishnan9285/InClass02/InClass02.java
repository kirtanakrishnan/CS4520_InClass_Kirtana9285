package com.example.inclass_krishnan9285.InClass02;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inclass_krishnan9285.R;

/*
Kirtana Krishnan
Assignment #2
 */
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

    private ImageView mood;




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
        mood = findViewById(R.id.happy);

        Result resultUser = new Result();

       ActivityResultLauncher<Intent> startActivityForResult =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                             if (result.getResultCode() == RESULT_OK) {
                                 Avatar avatar =
                                         result.getData().getParcelableExtra("Example Avatar");
                                 int imageRes = avatar.getImageResource();
                                selectAvatar.setImageResource(imageRes);
                                selectAvatar = findViewById(imageRes);
                                resultUser.setAvatarImageResource(imageRes);



                            }


                        }});

        selectAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSelectAvatar = new Intent(InClass02.this, SelectAvatar.class);
                startActivityForResult.launch(intentSelectAvatar);


            }
        });


        mood.setImageResource(R.drawable.happy);
        resultUser.setMoodImageResource(R.drawable.happy);
        seekBarMood.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b == true) {
                    if (i == 0) {
                        mood.setImageResource(R.drawable.angry);
                        resultUser.setMoodImageResource(R.drawable.angry);
                        currentMoodDisplay.setText("Angry");
                    } else if (i == 1) {
                        mood.setImageResource(R.drawable.sad);
                        resultUser.setMoodImageResource(R.drawable.sad);
                        currentMoodDisplay.setText("Sad");
                    } else if (i == 2) {
                        mood.setImageResource(R.drawable.happy);
                        resultUser.setMoodImageResource(R.drawable.happy);
                        currentMoodDisplay.setText("Happy");
                    } else if (i == 3) {
                        mood.setImageResource(R.drawable.awesome);
                        resultUser.setMoodImageResource(R.drawable.awesome);
                        currentMoodDisplay.setText("Awesome");
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameToString = editTextName.getText().toString().trim();
                String emailToString = editTextEmail.getText().toString().trim();
                if (nameToString.equals("")) {
                    Toast.makeText(InClass02.this, "Enter your name!", Toast.LENGTH_LONG).show();
                } else if (!(!emailToString.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToString).matches())){
                    Toast.makeText(InClass02.this, "Enter valid email address!", Toast.LENGTH_LONG).show();
                } else if (useGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(InClass02.this, "Please select a device!", Toast.LENGTH_LONG).show();
                } else {


                    resultUser.setEditTextEmail(emailToString);
                    resultUser.setEditTextName(nameToString);


                    int deviceID = useGroup.getCheckedRadioButtonId();
                    RadioButton deviceButton = (RadioButton) useGroup.findViewById(deviceID);
                    String device = (String) deviceButton.getText();
                    String moodDisplay = currentMoodDisplay.getText().toString().trim();


                    resultUser.setDevice(device);
                    resultUser.setMood(moodDisplay);


                    Intent toResult = new Intent(InClass02.this, ProfileResults.class);
                    toResult.putExtra("Example Result", resultUser);
                    startActivity(toResult);
                }

            }
        });



    }
}