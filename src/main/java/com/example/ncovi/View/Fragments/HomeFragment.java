package com.example.ncovi.View.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ncovi.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Objects;


public class HomeFragment extends Fragment {
View view;
FusedLocationProviderClient client;
private TextView tv_all , tv_full;
    SupportMapFragment mapFragment;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        iniUI();
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        latLng = new LatLng(17.232607, 106.799103);
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + ":" + latLng.longitude);
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng,20
                        ));
                    googleMap.addMarker(markerOptions);
                    }

                });
            }
        });
        return view;
    }

    private void iniUI() {
        tv_all = view.findViewById(R.id.tv_view_all);
        tv_full  = view.findViewById(R.id.tv_full);
        // gán cứng text
        String strAll = "Xem chi tiết";
        String strFull = "Mở rộng";
        // Tạo SpannableString
        SpannableString ss_all = new SpannableString(strAll);
        SpannableString ss_fll = new SpannableString(strFull);
        // tạo kiểu chữ
        UnderlineSpan line = new UnderlineSpan();
        // set giá trị text từ vị nào đến vị trí nòa
        ss_all.setSpan(line , 0 ,12 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        ss_fll.setSpan(line , 0 ,7 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        // trả dữ liệu lại cho textview
        tv_all.setText(ss_all);
        tv_full.setText(ss_fll);
        tv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://ncovi.vnpt.vn/views/ncovi_detail.html?fbclid=IwAR3fFSZUCKL8ZbayDujXA55q2G9AKb9RJ1diLpU6RblEKJB-9N8o9JBAkRA"));
                startActivity(intent);
            }
        });
    }

}