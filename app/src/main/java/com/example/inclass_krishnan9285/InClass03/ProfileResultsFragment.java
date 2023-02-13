package com.example.inclass_krishnan9285.InClass03;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inclass_krishnan9285.InClass02.Result;
import com.example.inclass_krishnan9285.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileResultsFragment extends Fragment {

    private Result resultUser;

    private TextView nameResult2;
    private TextView emailResult2;
    private TextView useResult2;
    private TextView iAm2;

    private ImageView avatarResult2;
    private ImageView moodResult2;
    private TextView nameText2;
    private TextView emailText2;
    private TextView useText2;
    private TextView amText2;

    public ProfileResultsFragment() {

    }



    public static ProfileResultsFragment newInstance(Result resultUser) {
        ProfileResultsFragment fragment = new ProfileResultsFragment();
        Bundle args = new Bundle();
        args.putParcelable("resultUser", resultUser);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            resultUser = getArguments().getParcelable("resultUser");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Display Activity");

        View view = inflater.inflate(R.layout.fragment_profile_results, container, false);

        nameResult2 = view.findViewById(R.id.nameResult2);
        emailResult2 = view.findViewById(R.id.emailResult2);
        useResult2 = view.findViewById(R.id.useResult2);
        iAm2 = view.findViewById(R.id.iAm2);

        avatarResult2 = view.findViewById(R.id.avatarResult2);
        avatarResult2.setImageResource(R.drawable.select_avatar);
        moodResult2 = view.findViewById(R.id.moodResult2);
        moodResult2.setImageResource(R.drawable.happy);
        nameText2 = view.findViewById(R.id.nameText2);
        emailText2 = view.findViewById(R.id.emailText2);
        useText2 = view.findViewById(R.id.useText2);
        amText2 = view.findViewById(R.id.amText2);

        nameText2.setText(resultUser.getEditTextName());
        emailText2.setText(resultUser.getEditTextEmail());
        useText2.setText(resultUser.getDevice());
        amText2.setText(resultUser.getMood());
        moodResult2.setImageResource(resultUser.getMoodImageResource());
        avatarResult2.setImageResource(resultUser.getAvatarImageResource());

        return view;

    }


}