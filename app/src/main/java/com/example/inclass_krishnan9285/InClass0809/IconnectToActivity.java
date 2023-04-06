package com.example.inclass_krishnan9285.InClass0809;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public interface IconnectToActivity {
    void populateMainFragment(FirebaseUser mUser);
    void populateRegisterFragment();
    void registerDone(FirebaseUser mUser, User user);
    void logoutPressed();
    void newMessageButtonPressedFromMainFragment(ArrayList<User> users);

    void onFriendSelectedFromSelectFriendFragment(User user);

    void onChatSelectedFromRecentChats(ChatRecord record);

    void populateEditProfileFragment();
}
