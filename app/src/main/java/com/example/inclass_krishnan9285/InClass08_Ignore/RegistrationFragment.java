package com.example.inclass_krishnan9285.InClass08_Ignore;

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

import com.example.inclass_krishnan9285.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment {

    private EditText firstName, lastName, displayNameRegister, email, passwordRegister,
            confirmPassword;

    private Button registerButton;
    private String firstNameString, lastNameString, displayNameRegisterString, emailString,
    passwordRegisterString, confirmPasswordString;

    private View registrationView;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    protected Message message = new Message();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private IRegisterFragmentAction mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof IRegisterFragmentAction){
            mListener = (IRegisterFragmentAction) context;
        } else{
            throw new RuntimeException(context.toString() +
                    "must implement IRegisterFragmentAction");
        }
    }

    public RegistrationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RegistrationFragment newInstance() {
        RegistrationFragment fragment = new RegistrationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        getActivity().setTitle("Register");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (registrationView != null) {
            return registrationView;
        }

        View view =  inflater.inflate(R.layout.fragment_registration, container, false);
        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        displayNameRegister = view.findViewById(R.id.displayNameRegister);
        email = view.findViewById(R.id.email);
        passwordRegister = view.findViewById(R.id.passwordRegister);
        confirmPassword = view.findViewById(R.id.confirmPassword);
        registerButton = view.findViewById(R.id.registerButton);

        registrationView = view;


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstNameString = String.valueOf(firstName.getText()).trim();
                lastNameString = String.valueOf(lastName.getText()).trim();
                displayNameRegisterString = String.valueOf(displayNameRegister.getText()).trim();
                emailString = String.valueOf(email.getText()).trim();
                passwordRegisterString = String.valueOf(passwordRegister.getText()).trim();
                confirmPasswordString = String.valueOf(confirmPassword.getText()).trim();

                if(firstNameString.equals("")){
                    firstName.setError("Must input first name!");
                }
                if(lastNameString.equals("")){
                    firstName.setError("Must input last name!");
                }
                if(displayNameRegisterString.equals("")){
                    email.setError("Must input display name!");
                }
                if(emailString.equals("")){
                    email.setError("Must input email!");
                }
                if(passwordRegisterString.equals("")){
                    passwordRegister.setError("Password must not be empty!");
                }
                if(!passwordRegisterString.equals(confirmPasswordString)){
                    confirmPassword.setError("Passwords must match!");
                }

//            Validation complete.....
                if(!firstNameString.equals("") && !lastNameString.equals("") &&
                        !displayNameRegisterString.equals("") &&
                        !emailString.equals("")
                        && !passwordRegisterString.equals("")
                        && passwordRegisterString.equals(confirmPasswordString)){

                    //              Firebase authentication: Create user.......
                    mAuth.createUserWithEmailAndPassword(emailString,
                                    passwordRegisterString)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){

                                        mUser = task.getResult().getUser();

//                                    Adding name to the FirebaseUser...
                                        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(displayNameRegisterString)
                                                .build();

                                        //Setting up database
                                        //Map<String, String> friend = new HashMap<>();
                                        //friend.put("email", mUser.getEmail());

                                        User user = new User(firstNameString, lastNameString, displayNameRegisterString, emailString);

                                       // Map<String, Object> user = new HashMap<>();
                                       // user.put("email", mUser.getEmail());
                                       // user.put("fname", mUser.getDisplayName());


                                        db.collection("users")
                                                .document(mUser.getEmail())
                                                .set(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Log.d("demo", "DocumentSnapshot added with ID: " );
                                                    }
                                                })
                                                /*.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Log.d("demo", "DocumentSnapshot added with ID: " + documentReference.getId());
                                                        message.setMessageID(mUser.getUid());
                                                    }
                                                })*/
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w("demo", "Error adding document", e);
                                                    }
                                                });


                                        mUser.updateProfile(profileChangeRequest)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){
                                                            mListener.registerDone(mUser);
                                                        }
                                                    }
                                                });

                                        Log.d("demo", "registration successful!");

                                    }
                                }
                            });

            }
        }





    });

        return registrationView;
    }
}