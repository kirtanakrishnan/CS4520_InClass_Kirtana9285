package com.example.inclass_krishnan9285;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class InClass03 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class03);
        setTitle("Edit Profile");


        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerFragment, new EditProfileFragment())
                .commit();
    }
}