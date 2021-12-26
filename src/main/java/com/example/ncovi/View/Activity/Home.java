package com.example.ncovi.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ncovi.Class.NetworkConnected.NetworkConnect;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Home extends AppCompatActivity {
    private user user;
    private BottomNavigationView bottomNavigationView;
    String username;
    TextView tv_name;
    private NetworkConnect networkConnect= new NetworkConnect();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        showFragments(new HomeFragment());
        DialogToast(Gravity.CENTER);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
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
                return true;
            }
        });
    }

    public void showFragments(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @SuppressLint("SetTextI18n")
    private void DialogToast(int gravity) {
        user = DataManager.loadUser();
        username = user.getName();
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_home);
        dialog.setCanceledOnTouchOutside(false);
        tv_name = dialog.findViewById(R.id.tv_name_home);
        Button btn_ok = dialog.findViewById(R.id.btn_dongy_home);
        Button btn_cancel = dialog.findViewById(R.id.btn_cancel_home);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = gravity;
        window.setAttributes(attributes);
        tv_name.setText("Xin Chào" + " " + username);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragments(new SucKhoeFragment());
                bottomNavigationView.setSelectedItemId(R.id.suckhoe);
                dialog.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

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