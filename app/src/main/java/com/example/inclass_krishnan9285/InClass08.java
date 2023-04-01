package com.example.inclass_krishnan9285;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InClass08 extends AppCompatActivity implements ILoginFragmentAction, IRegisterFragmentAction,
        IMainFragmentButtonAction, IMessagesListRecyclerAction {

    private AuthenticationFragment authenticationFragment;
    private MessengerFragment messengerFragment;
    private RegistrationFragment registrationFragment;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class08);
        setTitle("Login or Register");

        mAuth = FirebaseAuth.getInstance();

        /*authenticationFragment = new AuthenticationFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, authenticationFragment).addToBackStack(null)
                .commit();*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        populateScreen();
    }

    private void populateScreen() {
        //      Check for Authenticated users ....
        if(currentUser != null){
            //The user is authenticated, Populating The Main Fragment....
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, messengerFragment.newInstance()).addToBackStack(null)
                    .commit();

        }else{
//            The user is not logged in, load the login Fragment....
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, authenticationFragment.newInstance()).addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void populateMessengerFragment(FirebaseUser mUser) {
        this.currentUser = mUser;
        populateScreen();
    }

    @Override
    public void populateRegisterFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, registrationFragment.newInstance()).addToBackStack(null)
                .commit();
    }

    @Override
    public void registerDone(FirebaseUser mUser) {
        this.currentUser = mUser;
        populateScreen();
    }

    @Override
    public void logoutPressed() {

    }

    @Override
    public void editButtonClickedFromRecyclerView(Message message) {


    }

    @Override
    public void deleteButtonPressedFromRecyclerView(String friendEmail) {

    }
}