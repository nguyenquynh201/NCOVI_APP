package com.example.ncovi.View.Activity;

import static com.example.ncovi.View.Activity.LoginActivity.SAVE_OPEN_APP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.ncovi.Class.NetworkConnected.NetworkConnect;
import com.example.ncovi.R;
import com.example.ncovi.View.SharedPreference.MySharedPreference;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.RotatingCircle;

public class OnbroadingView_Activity extends AppCompatActivity {
    private ProgressBar progressBar;
    private MySharedPreference mySharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onbroading_view);
        progressBar = findViewById(R.id.progress);
        Circle circle = new Circle();
        progressBar.setIndeterminateDrawable(circle);
        circle.setColor(R.color.color_main);
        mySharedPreference = new MySharedPreference(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mySharedPreference.saveOpenApp(SAVE_OPEN_APP)) {
                    starActivity(LoginActivity.class);
                } else {
                    starActivity(OnBroadingMain_Activity.class);
                    mySharedPreference.putBooleanValue(SAVE_OPEN_APP, true);
                }
            }
        }, 3000);
    }

    private void starActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }


}