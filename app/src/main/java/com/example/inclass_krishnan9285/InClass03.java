package com.example.inclass_krishnan9285;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class InClass03 extends AppCompatActivity implements SendAvatar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class03);
        setTitle("Edit Profile");

    }

    @Override
    public void fromAvatar(int id) {

        EditProfileFragment fragment =
                (EditProfileFragment) getSupportFragmentManager().findFragmentByTag("fragment");

        fragment.displayReceivedAvatar(id);
    }

}