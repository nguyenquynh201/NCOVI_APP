package com.example.ncovi.Class.NetworkConnected;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.ncovi.R;

public class NetworkConnect extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(!Common.isConnectedInternet(context))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.custom_internet , null);
            builder.setView(view);
            Button btn_connect = view.findViewById(R.id.btn_connect);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            alertDialog.getWindow().setGravity(Gravity.CENTER);
            alertDialog.setCancelable(false);
            btn_connect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    onReceive(context, intent);
                }
            });
        }
    }
}
