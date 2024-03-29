package com.example.inclass_krishnan9285.InClass0809;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inclass_krishnan9285.R;

import java.util.ArrayList;


public class FragmentNewChatSelectFriend extends Fragment {

    private static final String ARG_USERS = "users";
    private static final String ARG_CURUSER = "mUser";

    // TODO: Rename and change types of parameters
    private ArrayList<User> users;
    private User currentUser;
    private RecyclerView recyclerViewFriends;
    private RecyclerView.LayoutManager recyclerViewFriendsLayoutManager;
    private FriendsAdapter friendsAdapter;

    public FragmentNewChatSelectFriend(ArrayList<User> users, User currentUser) {
        // Required empty public constructor
        this.users = users;
        this.currentUser =  currentUser;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_new_chat_select_friend, container, false);
//        remove the currentUser from users array list...
        users.remove(currentUser);
        //Log.d(Tags.TAG, "Current users: "+users.toString());
        recyclerViewFriends = rootView.findViewById(R.id.recyclerViewFriends);
        recyclerViewFriendsLayoutManager = new LinearLayoutManager(getContext());
        friendsAdapter = new FriendsAdapter(users,getContext());
        recyclerViewFriends.setLayoutManager(recyclerViewFriendsLayoutManager);
        recyclerViewFriends.setAdapter(friendsAdapter);
        return rootView;
    }
}