package com.example.ncovi.Class.NetworkConnected;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class Common {
    public static boolean isConnectedInternet(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager!=null)
        {
            NetworkInfo[] infos = connectivityManager.getAllNetworkInfo();
            if(infos!=null)
            {
                for(int i = 0 ; i<infos.length ; i++)
                {
                    if(infos[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
