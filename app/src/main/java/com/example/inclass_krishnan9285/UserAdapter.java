package com.example.inclass_krishnan9285;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    private ArrayList<User> users;

    private IMessagesListRecyclerAction listener;

    public UserAdapter() {
    }

    public UserAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        if (context instanceof IMessagesListRecyclerAction) {
            this.listener = (IMessagesListRecyclerAction) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement IMessagesListRecyclerAction");
        }

    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView userDisplayName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.userDisplayName = itemView.findViewById(R.id.userDisplayName);
        }

        public TextView getUserDisplayName() {
            return userDisplayName;
        }
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRecyclerView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.row_user, parent, false);

        return new UserAdapter.ViewHolder(itemRecyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User curUser = this.getUsers().get(position);

        holder.getUserDisplayName().setText(curUser.getDisplayname());

    }

    @Override
    public int getItemCount() {
        return this.getUsers().size();
    }






}
