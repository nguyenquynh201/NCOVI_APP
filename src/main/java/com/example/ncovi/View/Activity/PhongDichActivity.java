package com.example.ncovi.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toolbar;

import com.example.ncovi.Class.NetworkConnected.NetworkConnect;
import com.example.ncovi.Model.phongdich;
import com.example.ncovi.R;
import com.example.ncovi.View.Adaptor.PhongChongDichAdaptor;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class PhongDichActivity extends AppCompatActivity{
    private static final int REQUEST_CODE = 123;
    private Toolbar toolbar;
private YouTubePlayerView youTubePlayerView;
private PhongChongDichAdaptor phongChongDichAdaptor;
private List<phongdich> mList;
private RecyclerView recyclerView;
private NetworkConnect networkConnect= new NetworkConnect();
private String API_KEY = "AIzaSyClh99CCH1CJGx1IeV86iLrUbkvcRiVTNQ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_dich);
        recyclerView = findViewById(R.id.rcv_phongchong);
        getListData();
        onBack();
    }

    private void getListData() {
        mList = new ArrayList<>();
        mList.add(new phongdich(R.drawable.custom_bg_nhiembenh , R.drawable.custom_btn_nhiembenh , R.drawable.school , "Danh cho học sinh"));
        mList.add(new phongdich(R.drawable.custom_bg_binhphuc , R.drawable.custom_bg_sk , R.drawable.noilamviec , "Danh cho nơi làm việc"));
        mList.add(new phongdich(R.drawable.custom_bg_nhiembenh , R.drawable.custom_btn_nhiembenh , R.drawable.dichvu , "Danh cho khu dịch vụ"));
        phongChongDichAdaptor = new PhongChongDichAdaptor(mList , this);
        phongChongDichAdaptor.setData(mList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL  ,false));
        recyclerView.setAdapter(phongChongDichAdaptor);
    }


    private List<phongdich> getList() {
        List<phongdich> mListData = new ArrayList<>();

        return mListData;
    }




    @SuppressLint("UseSupportActionBar")
    private void onBack() {
//        toolbar = findViewById(R.id.toolbar_phongdich);
//        setActionBar(toolbar);
//        getSupportActionBar().setTitle("");
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

//Check connect
@Override
protected void onStart() {
    IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    registerReceiver(networkConnect , intentFilter);
    super.onStart();
}

    @Override
    protected void onStop() {
        unregisterReceiver(networkConnect);
        super.onStop();

    }
}