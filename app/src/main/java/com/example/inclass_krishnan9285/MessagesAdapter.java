package com.example.inclass_krishnan9285;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {
    private static final String TAG = "demo";

    private ArrayList<Message> messages;

    private IMessagesListRecyclerAction mListener;

    public MessagesAdapter() {
    }

    public MessagesAdapter(ArrayList<Message> messages, Context context) {
        this.messages = messages;
        Message message = new Message("test", "hi");
        Message message2 = new Message("test", "bye");

        this.messages.add(message);
        this.messages.add(message2);

        if (context instanceof IMessagesListRecyclerAction) {
            this.mListener = (IMessagesListRecyclerAction) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement IMessagesListRecyclerAction");
        }

    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewName;
        private final TextView messageTextView;

        //private final Button buttonAddEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.userName);
            this.messageTextView = itemView.findViewById(R.id.userDisplayName);
           // this.buttonAddEdit = itemView.findViewById(R.id.buttonAddEdit);
        }

        public TextView getTextViewName() {
            return textViewName;
        }

        public TextView getMessageTextView() {
            return messageTextView;
        }

       /*public Button getButtonMessage() {
            return buttonAddEdit;
        }*/

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRecyclerView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_row, parent, false);

        Log.d("demo", "loaded recyclerview");

        return new ViewHolder(itemRecyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Message curMessage = this.getMessages().get(position);

        holder.getTextViewName().setText(curMessage.getUser());
        holder.getMessageTextView().setText(curMessage.getMessage());



    }

    @Override
    public int getItemCount() {
        return this.getMessages().size();
    }
}

