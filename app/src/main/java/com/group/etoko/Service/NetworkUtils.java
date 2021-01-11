package com.group.etoko.Service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Objects;

public class NetworkUtils {
    public static boolean getConnectivityStatus(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info= Objects.requireNonNull(manager).getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}
