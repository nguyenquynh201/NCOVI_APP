package com.example.ncovi.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.ncovi.R;
import com.example.ncovi.View.Activity.LoginActivity;


public class OnBroading3Fragment extends Fragment {
private Button btn_onBoarding3;
    public OnBroading3Fragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_on_broading3, container, false);


        return mView;

    }
}