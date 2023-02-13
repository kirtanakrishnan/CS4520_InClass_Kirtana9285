package com.example.inclass_krishnan9285.InClass03;

import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.inclass_krishnan9285.InClass02.Result;
import com.example.inclass_krishnan9285.R;

public class EditProfileFragment extends Fragment {


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
    private ImageView mood;
    private int happyFragment;
    private Button submit2;

    InterfaceToAvatar interfaceToAvatar;
    InterfaceToResult interfaceToResult;

    View rootView;

    Result resultUser = new Result();


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
        editTextName2 = rootView.findViewById(R.id.editTextName2);
        editTextName2.setText(editTextNameFragment);

        editTextEmail2 = rootView.findViewById(R.id.editTextEmail2);
        editTextEmail2.setText(editTextEmailFragment);

        selectAvatar2 = rootView.findViewById(R.id.select_avatar2);
        iUse2 = rootView.findViewById(R.id.iUse2);

       useGroup2 = rootView.findViewById(R.id.useGroup2);
        android = rootView.findViewById(R.id.android);
        iOS = rootView.findViewById(R.id.iOS);

        currentMood2 = rootView.findViewById(R.id.currentMood2);

        currentMoodDisplay2 = rootView.findViewById(R.id.currentMoodDisplay2);

        seekBarMood2 = rootView.findViewById(R.id.seekBarMood2);

        mood = rootView.findViewById(R.id.happy2);

        submit2 = rootView.findViewById(R.id.submit2);

        selectAvatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceToAvatar.toAvatar();
            }
        });

        seekBarMood2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b == true) {
                    if (i == 0) {
                        mood.setImageResource(R.drawable.angry);
                        resultUser.setMoodImageResource(R.drawable.angry);
                        currentMoodDisplay2.setText("Angry");
                    } else if (i == 1) {
                        mood.setImageResource(R.drawable.sad);
                        resultUser.setMoodImageResource(R.drawable.sad);
                        currentMoodDisplay2.setText("Sad");
                    } else if (i == 2) {
                        mood.setImageResource(R.drawable.happy);
                        resultUser.setMoodImageResource(R.drawable.happy);
                        currentMoodDisplay2.setText("Happy");
                    } else if (i == 3) {
                        mood.setImageResource(R.drawable.awesome);
                        resultUser.setMoodImageResource(R.drawable.awesome);
                        currentMoodDisplay2.setText("Awesome");
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        resultUser.setMoodImageResource(R.drawable.happy);
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameToString = editTextName2.getText().toString().trim();
                String emailToString = editTextEmail2.getText().toString().trim();
                if (nameToString.equals("")) {
                    Toast.makeText(getActivity(), "Enter your name!", Toast.LENGTH_LONG).show();
                } else if (!(!emailToString.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToString).matches())){
                    Toast.makeText(getActivity(), "Enter valid email address!", Toast.LENGTH_LONG).show();
                } else if (useGroup2.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Please select a device!", Toast.LENGTH_LONG).show();
                } else {


                    resultUser.setEditTextEmail(emailToString);
                    resultUser.setEditTextName(nameToString);


                    int deviceID = useGroup2.getCheckedRadioButtonId();
                    RadioButton deviceButton = (RadioButton) useGroup2.findViewById(deviceID);
                    String device = (String) deviceButton.getText();
                    String moodDisplay = currentMoodDisplay2.getText().toString().trim();


                    resultUser.setDevice(device);
                    resultUser.setMood(moodDisplay);



                    interfaceToResult.toResult(resultUser);

                }

            }
        });

        return rootView;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof InterfaceToAvatar || context instanceof InterfaceToResult){
            interfaceToAvatar = (InterfaceToAvatar) context;
            interfaceToResult = (InterfaceToResult) context;
        } else{
            throw new RuntimeException(context.toString() + "must implement InterfaceToAvatar" +
                    " or InterfaceToResult");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectAvatar2 = (ImageView) view.findViewById(R.id.select_avatar2);

    }

    protected void displayReceivedAvatar(int id) {

        selectAvatar2.setImageResource(id);

        resultUser.setAvatarImageResource(id);
    }





}
