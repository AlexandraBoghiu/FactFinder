package com.fact.factsapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private TextView nameTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        sharedPreferences = requireActivity().getSharedPreferences("register prefs", Context.MODE_PRIVATE);
        nameTextView = view.findViewById(R.id.profile);

        nameTextView.setText(MainActivity.getEmailAddress());

        return view;
    }
}
