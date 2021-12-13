package com.example.ncovi.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ncovi.Model.user;
import com.example.ncovi.R;
import com.example.ncovi.View.Fragments.DanhMucFragment;
import com.example.ncovi.View.Fragments.FeedBackFragment;
import com.example.ncovi.View.Fragments.HomeFragment;
import com.example.ncovi.View.Fragments.QRFragment;
import com.example.ncovi.View.Fragments.SucKhoeFragment;
import com.example.ncovi.View.SharedPreference.DataManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {
private user user;
private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        showFragments(new HomeFragment());
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home :
                        showFragments(new HomeFragment());
                        break;
                    case R.id.suckhoe:
                        showFragments(new SucKhoeFragment());
                        break;
                    case R.id.qr_code:
                        showFragments(new QRFragment());
                        break;
                    case R.id.feedback:
                        showFragments(new FeedBackFragment());
                        break;
                    case R.id.danhmuc:
                        showFragments(new DanhMucFragment());
                        break;
                }
                return  true;
            }
        });
    }

    public void showFragments(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout , fragment);
        fragmentTransaction.commit();
    }

}