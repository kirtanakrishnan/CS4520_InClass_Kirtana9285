package com.example.inclass_krishnan9285;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class EditProfileFragment extends Fragment {

    final String TAG = "demo";

    private static final String NAME_TEXT = "textName";
    private static final String EMAIL_TEXT = "textEmail";
    private static final String AVATAR_TEXT = "selectAvatar";
    private static final String CURRMOOD_TEXT = "textCurrentMood";
    private static final String CURRMOODDISP = "textCurrentMoodDisplay";
    private static final String MOOD_TEXT = "happy";
    private EditText editTextName2;
    private String editTextNameFragment;
    private EditText editTextEmail2;
    private String editTextEmailFragment;
    private ImageView selectAvatar2;
    private int selectAvatarFragment;
    private TextView iUse2;
    private RadioButton android;
    private RadioButton iOS;

    private RadioGroup useGroup2;
    private TextView currentMood2;
    private String currentMoodFragment;
    private TextView currentMoodDisplay2;
    private String currentMoodDisplayFragment;
    private SeekBar seekBarMood2;
    private ImageView happy2;
    private int happyFragment;
    private Button submit2;

    InterfaceToAvatar interfaceToAvatar;

    View rootView;


    public EditProfileFragment() {

    }

    public static EditProfileFragment newInstance(String textName, String textEmail, int selectAvatar,
                                                  String textCurrentMood,
                                                  String textCurrentMoodDisplay, int happy) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        args.putString(NAME_TEXT, textName);
        args.putString(EMAIL_TEXT, textEmail);
        args.putInt(AVATAR_TEXT, R.id.select_avatar2);
        args.putString(CURRMOOD_TEXT, textCurrentMood);
        args.putString(CURRMOODDISP, textCurrentMoodDisplay);
        args.putInt(MOOD_TEXT, happy);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "FirstFragment onCreate: ");
        if (getArguments() != null) {
            editTextNameFragment = getArguments().getString(NAME_TEXT);
            editTextEmailFragment = getArguments().getString(EMAIL_TEXT);
            selectAvatarFragment = getArguments().getInt(AVATAR_TEXT);
            currentMoodFragment = getArguments().getString(CURRMOOD_TEXT);
            currentMoodDisplayFragment = getArguments().getString(CURRMOODDISP);
            happyFragment = getArguments().getInt(MOOD_TEXT);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView != null) {
            return rootView;
        }

        View view = inflator.inflate(R.layout.fragment_edit_profile, container, false);
        rootView = view;
        Log.d(TAG, "FirstFragment onCreateView: ");
        editTextName2 = rootView.findViewById(R.id.editTextName2);
        editTextName2.setText(editTextNameFragment);

        editTextEmail2 = rootView.findViewById(R.id.editTextEmail2);
        editTextEmail2.setText(editTextEmailFragment);

        selectAvatar2 = rootView.findViewById(R.id.select_avatar2);
     // selectAvatar2.setImageResource(selectAvatarFragment);
        iUse2 = rootView.findViewById(R.id.iUse2);

       useGroup2 = rootView.findViewById(R.id.useGroup2);
        android = rootView.findViewById(R.id.android);
        iOS = rootView.findViewById(R.id.iOS);

        currentMood2 = rootView.findViewById(R.id.currentMood2);
       // currentMood2.setText(currentMoodFragment);

        currentMoodDisplay2 = rootView.findViewById(R.id.currentMoodDisplay2);
       // currentMoodDisplay2.setText(currentMoodDisplayFragment);

        seekBarMood2 = rootView.findViewById(R.id.seekBarMood2);

        happy2 = rootView.findViewById(R.id.happy2);
       // happy2.setImageResource(happyFragment);

        submit2 = rootView.findViewById(R.id.submit2);

        selectAvatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceToAvatar.toAvatar();
            }
        });

        return rootView;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof InterfaceToAvatar){
            interfaceToAvatar = (InterfaceToAvatar) context;
        } else{
            throw new RuntimeException(context.toString() + "must implement InterfaceToAvatar");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectAvatar2 = (ImageView) view.findViewById(R.id.select_avatar2);

    }

    protected void displayReceivedAvatar(int id) {

        selectAvatar2.setImageResource(id);
    }





}
