package com.example.inclass_krishnan9285;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessengerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessengerFragment extends Fragment {

    private static final String ARG_MESSAGES = "messages array";
    private static final String ARG_USERS = "users array";

    private String displayName;
    private EditText editTextMessage;
    private Button buttonAddEdit;

    private RecyclerView recyclerView;
    private MessagesAdapter messagesAdapter;
    private UserAdapter userAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private ArrayList<Message> mMessages;
    private ArrayList<User> mUsers;
    private Message mMessage;


    private Boolean isEdit = false;

    private IMainFragmentButtonAction mListener;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    public MessengerFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MessengerFragment newInstance() {
        MessengerFragment fragment = new MessengerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MESSAGES, new ArrayList<Message>());
        args.putSerializable(ARG_USERS, new ArrayList<User>());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Group Messenger");
        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey(ARG_MESSAGES)) {
                mMessages = (ArrayList<Message>) args.getSerializable(ARG_MESSAGES);
            }
            if (args.containsKey(ARG_USERS)) {
                mUsers = (ArrayList<User>) args.getSerializable(ARG_USERS);
            }

            //            Initializing Firebase...
            db = FirebaseFirestore.getInstance();
            mAuth = FirebaseAuth.getInstance();
            mUser = mAuth.getCurrentUser();

            this.displayName = mUser.getDisplayName();

            //            Loading initial data...
            loadData();
           // loadUserData();
        }

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof IMainFragmentButtonAction){
            mListener = (IMainFragmentButtonAction) context;
        }else{
            throw new RuntimeException(context.toString()+ "must implement IMainFragmentButtonAction");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View messengerView =  inflater.inflate(R.layout.fragment_messenger, container, false);

        editTextMessage = messengerView.findViewById(R.id.messageEditText);
        buttonAddEdit = messengerView.findViewById(R.id.buttonAddEdit);


        //      Setting up RecyclerView........
        recyclerView = messengerView.findViewById(R.id.recyclerReview);
        recyclerViewLayoutManager = new LinearLayoutManager(getContext());
        messagesAdapter = new MessagesAdapter(mMessages, getContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setAdapter(messagesAdapter);

        buttonAddEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Edit button.....
                if(isEdit){

                    Message mMessage = new Message();
                    String message = String.valueOf(editTextMessage.getText());
                    String displayName = mUser.getDisplayName();

                   /* db.collection("users")
                            .document(mUser.getEmail())
                            .collection("friends")
                            .document(mMessage.getMessage())
                            .delete();*/

                    mMessage = new Message(message);

                    addToFirebase(mMessage);
                    clearFields();
                    disableEdit();
//                Add button....
                }else{
                    String message = String.valueOf(editTextMessage.getText());
                    String displayName = mUser.getDisplayName();
                   // String messageID = mMessage.getMessageID();
                    mMessage= new Message(displayName, message);
                    addToFirebase(mMessage);
                    clearFields();
                }


            }
        });


        String email = mUser.getEmail();

        db.collection("users")
                .document(email)
                .collection("messages")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error!=null){
                            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }else{
//                            retrieving all the elements from Firebase....
                            ArrayList<Message> newMessages = new ArrayList<>();
                            for(DocumentSnapshot document : value.getDocuments()){
                                newMessages.add(document.toObject(Message.class));
                            }
//                            replace all the item in the current RecyclerView with the received elements...
                            messagesAdapter.setMessages(newMessages);
                            messagesAdapter.notifyDataSetChanged();
                        }
                    }
                });
              /*  .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error!=null){
                            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }else{
//                            retrieving all the elements from Firebase....
                            ArrayList<Message> newMessages = new ArrayList<>();
                            //for(DocumentSnapshot document : value.getDocuments()){
                            //    newMessages.add(document.toObject(Message.class));
                            //  }
                            for(DocumentSnapshot document : value.getDocuments()) {
                                newMessages.add(document.toObject(Message.class));
                            }
//                            replace all the item in the current RecyclerView with the received elements...
                            messagesAdapter.setMessages(newMessages);
                            messagesAdapter.notifyDataSetChanged();
                        }
                    }
                });*/

        return messengerView;
    }

    private void addToFirebase(Message message) {
        //        Add the new message to Firebase Cloud Firestore...

        String email = mUser.getEmail();


        db.collection("users")
                .document(email)
                .collection("messages")
                .add(mMessage)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("demo", "Message added: " + documentReference.getId());
                        Toast.makeText(getContext(),"Message added!", Toast.LENGTH_SHORT).show();
                      //  loadData();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("demo", "Error adding message", e);
                        Toast.makeText(getContext(), "Failed to add a message! Try again!", Toast.LENGTH_SHORT).show();
                    }
                });




    }

    //    Updating the RecyclerView when something gets changed...
    public void updateRecyclerView(ArrayList<Message> messages){
        this.mMessages = messages;
        Log.d("demo", "updates recyclerview");
        messagesAdapter.notifyDataSetChanged();
    }

    /*public void updateUserRecyclerView(ArrayList<User> friends){
        this.mUsers = friends;
        userAdapter.notifyDataSetChanged();
    }*/

    //    Enabling Edit.....
    public void enableEdit(Message message){
        this.isEdit = true;
        this.mMessage = message;
        editTextMessage.setText(mMessage.getMessage());
        buttonAddEdit.setText("Edit");
    }

    //    Disabling Edit.....
    public void disableEdit(){
        this.isEdit = false;
        buttonAddEdit.setText("Add");
    }

   public void clearFields(){
        editTextMessage.setText("");
    }

    private void loadData() {
        ArrayList<Message> messages = new ArrayList<>();
        String email = mUser.getEmail();


        db.collection("users")
                .document(email)
                .collection("messages")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
//
                                Log.d("demo", "loaded messages");
                                Message message = documentSnapshot.toObject(Message.class);
                                messages.add(message);

                            }
                            updateRecyclerView(messages);
                        }
                    }
                });
    }

    /*private void loadUserData() {
        ArrayList<User> friends = new ArrayList<>();
        db.collection("users")
                .document(mUser.getEmail())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
//                                Just like GSON..... Friend has to be Serializable,
//                                has to exactly match the variable names with the keys in the documents,
//                                and must have getters, setters, and toString() ....

                                User friend = documentSnapshot.toObject(User.class);
                                friends.add(friend);

                            }
                            updateUserRecyclerView(friends);
                        }
                    }
                });
    }*/

    public void deleteUser(String messageEmail) {
        db.collection("users")
               // .document(mUser.getEmail())
              //  .collection("friends")
                .document(messageEmail)
                .delete();
    }

}