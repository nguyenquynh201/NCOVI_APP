package com.example.ncovi.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.ncovi.R;
import com.example.ncovi.View.Activity.LoginActivity;
import com.example.ncovi.View.Activity.ThongTinCaNhanActivity;


public class DanhMucFragment extends Fragment {
private CardView cardView , cardView_logout;
    View mView;
    public DanhMucFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_danhmuc, container, false);
        iniUI();
        return mView;
    }

    private void iniUI() {
        cardView = mView.findViewById(R.id.tt_canhan);
        cardView_logout = mView.findViewById(R.id.tt_logout);
        cardView_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , LoginActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();

            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , ThongTinCaNhanActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
}