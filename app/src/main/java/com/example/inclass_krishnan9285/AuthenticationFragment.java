package com.example.inclass_krishnan9285;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inclass_krishnan9285.InClass03.InterfaceSendAvatar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AuthenticationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuthenticationFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String DISPLAY_NAME = "displayName";
    private static final String PASSWORD = "password";
    private static final String LOGIN = "loginButton";
    private static final String REGISTER_NOW = "register";
    private static final String REGISTER = "registerButton";



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private EditText displayName;
    private EditText password;
    private Button loginButton;
    private TextView registerNowTextView;
    private Button registerNowButton;

    private String displayNameText;
    private String passwordText;
    private String registerNowText;
    private String displayNameFragment;
    private String passwordFragment;
    private ILoginFragmentAction mListener;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    FirebaseFirestore db;



    public AuthenticationFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AuthenticationFragment newInstance() {
        AuthenticationFragment fragment = new AuthenticationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ILoginFragmentAction){
            mListener = (ILoginFragmentAction) context;
        } else{
            throw new RuntimeException(context.toString()+ "must implement ILoginFragmentAction");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View authenticationView = inflater.inflate(R.layout.fragment_authentication, container, false);


        displayName = authenticationView.findViewById(R.id.displayName);
        password = authenticationView.findViewById(R.id.password);
        loginButton = authenticationView.findViewById(R.id.loginButton);
        registerNowTextView = authenticationView.findViewById(R.id.registerNowTextView);
        registerNowButton = authenticationView.findViewById(R.id.registerNowButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("demo", "onClick: Login");
                displayNameText = displayName.getText().toString().trim();
                passwordText = password.getText().toString().trim();
                if(displayNameText.equals("")){
                    displayName.setError("Must input email!");
                }
                if(passwordText.equals("")){
                    password.setError("Password must not be empty!");
                }
                if(!displayNameText.equals("") && !passwordText.equals("")){
//                    Sign in to the account....
                    mAuth.signInWithEmailAndPassword(displayNameText, passwordText)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {


                                    Toast.makeText(getContext(), "Login Successful!", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), "Login Failed!"+e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        mListener.populateMessengerFragment(mAuth.getCurrentUser());
                                    }
                                }

                            })
                    ;
                }

            }
        });

        registerNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("demo", "onClick: Register");
                mListener.populateRegisterFragment();
            }
        });


        return authenticationView;

    }


}