package com.example.inclass_krishnan9285;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AvatarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AvatarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String FEM_6 = "female6";
    private static final String FEM_7 = "female7";
    private static final String FEM_8 = "female8";
    private static final String MALE_6 = "male6";
    private static final String MALE_7 = "male7";
    private static final String MALE_8 = "male8";


    // TODO: Rename and change types of parameters
    private ImageView avatarf6;
    private int avatarf6fragment;
    private ImageView avatarf7;
    private int avatarf7fragment;
    private ImageView avatarf8;
    private int avatarf8fragment;
    private ImageView avatarm6;
    private int avatarm6fragment;
    private ImageView avatarm7;
    private int avatarm7fragment;
    private ImageView avatarm8;
    private int avatarm8fragment;

    InterfaceSendAvatar sendAvatar;

    public AvatarFragment() {
        // Required empty public constructor
    }

    public static AvatarFragment newInstance(int avatarf6fragment, int avatarf7fragment,
                                             int avatarf8fragment, int avatarm6fragment,
                                             int avatarm7fragment, int avatarm8fragment) {
        AvatarFragment fragment = new AvatarFragment();
        Bundle args = new Bundle();
        args.putInt(FEM_6, avatarf6fragment);
        args.putInt(FEM_7, avatarf7fragment);
        args.putInt(FEM_8, avatarf8fragment);
        args.putInt(MALE_6, avatarm6fragment);
        args.putInt(MALE_7, avatarm7fragment);
        args.putInt(MALE_8, avatarm8fragment);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            avatarf6fragment = getArguments().getInt(FEM_6);
            avatarf7fragment = getArguments().getInt(FEM_7);
            avatarf8fragment = getArguments().getInt(FEM_8);
            avatarm6fragment = getArguments().getInt(MALE_6);
            avatarm7fragment = getArguments().getInt(MALE_7);
            avatarm8fragment = getArguments().getInt(MALE_8);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View avatarView = inflater.inflate(R.layout.fragment_avatar, container, false);

        avatarf6 = avatarView.findViewById(R.id.avatarf6);
       // avatarf6.setImageResource(avatarf6fragment);

        avatarf7 = avatarView.findViewById(R.id.avatarf7);
       // avatarf7.setImageResource(avatarf7fragment);

        avatarf8 = avatarView.findViewById(R.id.avatarf8);
      //  avatarf8.setImageResource(avatarf8fragment);

        avatarm6 = avatarView.findViewById(R.id.avatarm6);
      //  avatarm6.setImageResource(avatarm6fragment);

        avatarm7 = avatarView.findViewById(R.id.avatarm7);
       // avatarm7.setImageResource(avatarm7fragment);

        avatarm8 = avatarView.findViewById(R.id.avatarm8);
     //   avatarm8.setImageResource(avatarm8fragment);

        avatarf6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAvatar.fromAvatar(R.drawable.avatar_f_3);
            }
        });

        avatarf7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAvatar.fromAvatar(R.drawable.avatar_f_2);
            }
        });

        avatarf8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAvatar.fromAvatar(R.drawable.avatar_f_1);


            }
        });

        avatarm6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAvatar.fromAvatar(R.drawable.avatar_m_3);
            }
        });

        avatarm7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAvatar.fromAvatar(R.drawable.avatar_m_1);
            }
        });

        avatarm8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAvatar.fromAvatar(R.drawable.avatar_m_2);
            }
        });
        return avatarView;


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof InterfaceSendAvatar){
               sendAvatar = (InterfaceSendAvatar) context;
        } else{
               throw new RuntimeException(context.toString()+ "must implement InterfaceSendAvatar");
        }
    }
}