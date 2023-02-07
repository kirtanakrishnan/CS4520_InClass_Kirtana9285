package com.example.inclass_krishnan9285;

import android.content.Context;
import android.content.Intent;
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

import com.example.inclass_krishnan9285.InClass02.InClass02;
import com.example.inclass_krishnan9285.InClass02.SelectAvatar;

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
    private RadioGroup iUse2;
    private RadioButton android;
    private RadioButton iOS;
    private TextView currentMood2;
    private String currentMoodFragment;
    private TextView currentMoodDisplay2;
    private String currentMoodDisplayFragment;
    private SeekBar seekBarMood2;
    private ImageView happy2;
    private int happyFragment;
    private Button submit2;


   //k IfromFragmentToActivity sendData;

    public EditProfileFragment() {

    }

    public static EditProfileFragment newInstance(String textName, String textEmail, int selectAvatar,
                                                  String textCurrentMood,
                                                  String textCurrentMoodDisplay, int happy) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        args.putString(NAME_TEXT, textName);
        args.putString(EMAIL_TEXT, textEmail);
        args.putInt(AVATAR_TEXT, selectAvatar);
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
        View rootView = inflator.inflate(R.layout.activity_in_class03, container, false);
        Log.d(TAG, "FirstFragment onCreateView: ");
        editTextName2 = rootView.findViewById(R.id.editTextName2);
        editTextName2.setText(editTextNameFragment);

        editTextEmail2 = rootView.findViewById(R.id.editTextEmail2);
        editTextEmail2.setText(editTextEmailFragment);

        selectAvatar2 = rootView.findViewById(R.id.select_avatar2);
        selectAvatar2.setImageResource(selectAvatarFragment);

        //iUse2 = rootView.findViewById(R.id.iUse2);
        android = rootView.findViewById(R.id.android);
        iOS = rootView.findViewById(R.id.iOS);

        currentMood2 = rootView.findViewById(R.id.currentMood2);
        currentMood2.setText(currentMoodFragment);

        currentMoodDisplay2 = rootView.findViewById(R.id.currentMoodDisplay2);
        currentMoodDisplay2.setText(currentMoodDisplayFragment);

        seekBarMood2 = rootView.findViewById(R.id.seekBarMood2);

        happy2 = rootView.findViewById(R.id.happy2);
        happy2.setImageResource(happyFragment);

        submit2 = rootView.findViewById(R.id.submit2);


        selectAvatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });


        /*@Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
.
            if (context instanceof IfromFragmentToActivity){
                sendData = (IfromFragmentToActivity) context;
            }else{
                throw new RuntimeException(context.toString()+ "must implement IfromFragmentToActivity");
            }

        }

        interface IfromFragmentToActivity{
            void fromFragment(boolean isOn, String message);
        }**/
    return rootView;

    }
}
