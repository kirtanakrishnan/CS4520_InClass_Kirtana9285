package com.example.inclass_krishnan9285.InClass03;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inclass_krishnan9285.InClass02.Result;
import com.example.inclass_krishnan9285.R;

/*
Kirtana Krishnan
Assignment #3
 */
public class InClass03 extends AppCompatActivity implements InterfaceSendAvatar, InterfaceToAvatar,
        InterfaceToResult {

    private EditProfileFragment editProfileFragment;
    private AvatarFragment avatarFragment;

    private ProfileResultsFragment profileResultsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class03);
        setTitle("Edit Profile");
        editProfileFragment = new EditProfileFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragment, editProfileFragment).addToBackStack(null)
                .commit();

    }


    @Override
    public void fromAvatar(int id) {

       getSupportFragmentManager().popBackStack();
        setTitle("Edit Profile");
       editProfileFragment.displayReceivedAvatar(id);
    }

    @Override
    public void toAvatar() {
        avatarFragment = new AvatarFragment();

       getSupportFragmentManager().beginTransaction()
               .replace(R.id.containerFragment, avatarFragment)
               .addToBackStack(null)
                .commit();


    }

    @Override
    public void toResult(Result resultUser) {
       profileResultsFragment = new ProfileResultsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragment, profileResultsFragment.newInstance(resultUser))
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onBackPressed() {
        setTitle("Edit Profile");
        if(getSupportFragmentManager().getBackStackEntryCount()>1){
            super.onBackPressed();
        }
    }




}