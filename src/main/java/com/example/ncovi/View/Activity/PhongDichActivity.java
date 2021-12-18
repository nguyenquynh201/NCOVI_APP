package com.example.ncovi.View.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.ncovi.Model.phongdich;
import com.example.ncovi.R;
import com.example.ncovi.View.Adaptor.PhongChongDichAdaptor;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
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
        mList = getList();
        phongChongDichAdaptor = new PhongChongDichAdaptor(getList() , this);
        phongChongDichAdaptor.setData(getList());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL  ,false));
        recyclerView.setAdapter(phongChongDichAdaptor);
    }

    private List<phongdich> getList() {
        List<phongdich> mListData = new ArrayList<>();
        mListData.add(new phongdich(R.drawable.custom_bg_nhiembenh , R.drawable.custom_btn_nhiembenh , R.drawable.school , "Danh cho học sinh"));
        mListData.add(new phongdich(R.drawable.custom_bg_binhphuc , R.drawable.custom_bg_sk , R.drawable.school , "Danh cho nơi làm việc"));
        mListData.add(new phongdich(R.drawable.custom_bg_nhiembenh , R.drawable.custom_btn_nhiembenh , R.drawable.school , "Danh cho khu dịch vụ"));
        return mListData;
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode == REQUEST_CODE)
//        {
//            youTubePlayerView.initialize(API_KEY , (YouTubePlayer.OnInitializedListener) PhongDichActivity.this);
//        }
//    }

    @SuppressLint("UseSupportActionBar")
    private void onBack() {
//        toolbar = findViewById(R.id.toolbar_phongdich);
//        setActionBar(toolbar);
//        getSupportActionBar().setTitle("");
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

//    private ActionBar getSupportActionBar() {
//
//    }
//public class PhongDich extends AppCompatActivity{
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//}
}