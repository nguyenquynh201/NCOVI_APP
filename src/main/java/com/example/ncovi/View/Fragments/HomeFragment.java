package com.example.ncovi.View.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ncovi.Model.ModelCovid.covid;
import com.example.ncovi.R;
import com.example.ncovi.View.Activity.KhaiBaoTuNguyenActivity;
import com.example.ncovi.View.Activity.PhongDichActivity;
import com.example.ncovi.View.Activity.TheWorldActivity;
import com.example.ncovi.ViewModel.Response.CovidViewModel;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Objects;


public class HomeFragment extends Fragment {
    View view;
    private Button btn_huongdan , btn_khaibao;
    private TextView tv_all, tv_full, tv_nhiembenh, tv_tuvong, tv_khoibenh, tv_count_soCa, tv_count_tuvong, tv_count_binhphuc;
    SupportMapFragment mapFragment;
    private CovidViewModel covidViewModel;
    private covid covids;
    private LinearLayout lnl_home;
    String strNhiembenh, strTuvong, strKhoibenh, strCountKhoiBenh, strCountTuVong, strCountNhiemBenh;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iniUI();
        covidViewModel = new ViewModelProvider(getActivity()).get(CovidViewModel.class);
        covidViewModel.getCovid().observe(getActivity(), new Observer<covid>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(covid covid) {
                covids = covid;
                if (covids != null) {
                    strNhiembenh = String.valueOf(covid.getCases());
                    strKhoibenh = String.valueOf(covid.getRecovered());
                    strTuvong = String.valueOf(covid.getDeaths());
                    strCountKhoiBenh = String.valueOf(covid.getTodayRecovered());
                    strCountNhiemBenh = String.valueOf(covid.getTodayCases());
                    strCountTuVong = String.valueOf(covid.getTodayDeaths());
                    tv_nhiembenh.setText(strNhiembenh);
                    tv_tuvong.setText(strTuvong);
                    tv_khoibenh.setText(strKhoibenh);
                    tv_count_soCa.setText("+"+strCountNhiemBenh);
                    tv_count_binhphuc.setText("+"+strCountKhoiBenh);
                    tv_count_tuvong.setText("+"+strCountTuVong);
                }
            }
        });
        covidViewModel.iniDataCovid();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        latLng = new LatLng(17.232607, 106.799103);
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + ":" + latLng.longitude);
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 20
                        ));
                        googleMap.addMarker(markerOptions);
                    }

                });
            }
        });
        return view;
    }

    private void iniUI() {
        // ánh xạ textview
        tv_all = view.findViewById(R.id.tv_view_all);
        tv_full = view.findViewById(R.id.tv_full);
        tv_nhiembenh = view.findViewById(R.id.tv_nhiembenh);
        tv_tuvong = view.findViewById(R.id.tv_tuvong);
        tv_khoibenh = view.findViewById(R.id.tv_binhphuc);
        tv_count_soCa = view.findViewById(R.id.tv_count_soCa);
        tv_count_binhphuc = view.findViewById(R.id.tv_count_binhphuc);
        tv_count_tuvong = view.findViewById(R.id.tv_count_tuvong);
        // ánh xạ button
        btn_huongdan = view.findViewById(R.id.btn_huongdan);
        btn_khaibao = view.findViewById(R.id.btn_khaibao);
        btn_khaibao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , KhaiBaoTuNguyenActivity.class);
                getActivity().startActivity(intent);

            }
        });
        btn_huongdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , PhongDichActivity.class);
                getActivity().startActivity(intent);
            }
        });
        // ánh xạ linearlayout
        lnl_home = view.findViewById(R.id.covid_work);
        lnl_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , TheWorldActivity.class);
                startActivity(intent);
            }
        });
        // gán cứng text
        String strAll = "Xem chi tiết";
        String strFull = "Mở rộng";
        // Tạo SpannableString
        SpannableString ss_all = new SpannableString(strAll);
        SpannableString ss_fll = new SpannableString(strFull);
        // tạo kiểu chữ
        UnderlineSpan line = new UnderlineSpan();
        // set giá trị text từ vị nào đến vị trí nòa
        ss_all.setSpan(line, 0, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_fll.setSpan(line, 0, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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