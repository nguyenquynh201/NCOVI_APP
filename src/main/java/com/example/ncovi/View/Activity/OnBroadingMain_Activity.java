package com.example.ncovi.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ncovi.Class.NetworkConnected.NetworkConnect;
import com.example.ncovi.R;
import com.example.ncovi.View.Adaptor.OnBroadingAdaptor;

import me.relex.circleindicator.CircleIndicator;

public class OnBroadingMain_Activity extends AppCompatActivity {
    private ViewPager viewPager_onBoarding;
    private OnBroadingAdaptor onBroadingAdaptor;
    private Button btn_onBoarding;
    private CircleIndicator indicator_onBoarding;
    private NetworkConnect networkConnect = new NetworkConnect();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_broading_main);
        iniUI();

    }

    private void iniUI() {
        btn_onBoarding = findViewById(R.id.btn_onBoarding);
        indicator_onBoarding = findViewById(R.id.circle);
        viewPager_onBoarding = findViewById(R.id.viewPager_OnBroading);
        onBroadingAdaptor = new OnBroadingAdaptor(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager_onBoarding.setAdapter(onBroadingAdaptor);
        indicator_onBoarding.setViewPager(viewPager_onBoarding);
        indicator_onBoarding.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    btn_onBoarding.setText("Bắt Đầu");

                } else {
                    btn_onBoarding.setText("Skip");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btn_onBoarding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager_onBoarding.getCurrentItem() < 2) {
                    viewPager_onBoarding.setCurrentItem(viewPager_onBoarding.getCurrentItem() + 1);
                } else {

                    Intent intent = new Intent(OnBroadingMain_Activity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    //Check connect
    @Override
    protected void onStart() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkConnect, intentFilter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkConnect);
        super.onStop();

    }
}