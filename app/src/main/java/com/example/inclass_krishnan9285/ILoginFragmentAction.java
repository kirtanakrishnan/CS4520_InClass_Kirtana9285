package com.example.inclass_krishnan9285;

import com.google.firebase.auth.FirebaseUser;

public interface ILoginFragmentAction {
    void populateMessengerFragment(FirebaseUser mUser);
    void populateRegisterFragment();
}
