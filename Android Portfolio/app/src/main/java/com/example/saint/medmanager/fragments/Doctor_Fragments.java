package com.example.saint.medmanager.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saint.medmanager.R;

public class Doctor_Fragments extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       //Declaring the views
        View view = inflater.inflate(R.layout.fragment_doctors, container, false);

        return view;
    }
}
