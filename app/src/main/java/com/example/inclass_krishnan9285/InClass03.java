package com.example.inclass_krishnan9285;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Objects;

public class InClass03 extends AppCompatActivity implements InterfaceSendAvatar, InterfaceToAvatar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_in_class03);
        setTitle("Edit Profile");



       // EditProfileFragment fragment = new EditProfileFragment();


     getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragment, new EditProfileFragment()).addToBackStack(null)
                .commit();



    }




    @Override
    public void fromAvatar(int id) {

       getSupportFragmentManager().popBackStack();
    EditProfileFragment fragment =
                (EditProfileFragment) getSupportFragmentManager().
                        findFragmentById(R.id.editProfileLayout);

    fragment.displayReceivedAvatar(id);
    }

    @Override
    public void toAvatar() {
    // AvatarFragment avatarFragment =
     //        (AvatarFragment) getSupportFragmentManager();

    //  AvatarFragment avatarFragment = new AvatarFragment();


       getSupportFragmentManager().beginTransaction()
               .replace(R.id.containerFragment, new AvatarFragment())
               //.replace(R.id.selectAvatarLayout, new AvatarFragment(), "fragmentAvatar")
               .addToBackStack(null)
                .commit();
    }



}