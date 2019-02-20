package com.kredivation.ago.fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kredivation.ago.R;
import com.kredivation.ago.activity.LoginActivity;
import com.kredivation.ago.activity.MainActivity;
import com.kredivation.ago.utility.FontManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return view;
    }

    private void init() {
        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(getContext(), "fonts/materialdesignicons-webfont.otf");
        Typeface roboto_regular = FontManager.getFontTypefaceMaterialDesignIcons(getContext(), "fonts/roboto.regular.ttf");
        Typeface roboto_medium = FontManager.getFontTypefaceMaterialDesignIcons(getContext(), "fonts/roboto.medium.ttf");
        Typeface roboto_italic = FontManager.getFontTypefaceMaterialDesignIcons(getContext(), "fonts/roboto.italic.ttf");
        TextView logo = (TextView) view.findViewById(R.id.logo);
        logo.setTypeface(roboto_medium);
        TextView complete = (TextView) view.findViewById(R.id.complete);
        complete.setTypeface(roboto_italic);
        TextView quick = (TextView) view.findViewById(R.id.quick);
        quick.setTypeface(roboto_regular);
        TextView denting = (TextView) view.findViewById(R.id.denting);
        denting.setTypeface(roboto_regular);



        LinearLayout clean = view.findViewById(R.id.clean);
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        LinearLayout dent = view.findViewById(R.id.dent);
        dent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        LinearLayout genralservice = view.findViewById(R.id.genralservice);
        genralservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        LinearLayout breadown = view.findViewById(R.id.breadown);
        breadown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
